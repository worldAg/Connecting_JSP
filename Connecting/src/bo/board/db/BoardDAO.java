package bo.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class BoardDAO {

	private DataSource ds;

	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("Oracle DB 연결 실패: " + e.getMessage());
		}
	}
	
	private String textLengthOverCut(String text, int len) {
		if (text.length() >= len) {
			return text.substring(0, len) + "...";
		}
		else return text;
	}
	
	// 게시글 개수
	public int getListCount(String category, String loc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		
		String where = null;
		if (category.equals("all") && loc.equals("all")) {
			where = "";
		} else if (category.equals("all") && !loc.equals("all")) {
			where = "WHERE LOC = " + loc;
		} else if (!category.equals("all") && loc.equals("all")) {
			where = "WHERE CATEGORY = " + category;
		} else {
			where = "WHERE CATEGORY = " + category + "AND LOC = " + loc;
		}
		
		String sql = "SELECT COUNT(*) FROM BOARD " + where + "";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getListCount() error: " + e.getMessage());
			e.printStackTrace();
		} finally {	
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생");
			}
		}
		return total;
	}
	
	// 게시글 리스트 출력
	public List<Board> getBoardList(int page, int limit, String orderby, String category, String loc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sort = null;
		if (orderby.equals("0")) {
			sort = " BOARD_ID DESC";
		} else if (orderby.equals("1")) {
			sort = " HEART_COUNT DESC, BOARD_ID DESC";
		} else if (orderby.equals("2")) {
			sort = " START_DATE ASC, BOARD_ID DESC";
		} else if (orderby.equals("3")) {
			sort = " END_DATE ASC, BOARD_ID DESC";
		}
		
		String where = null;
		if (category.equals("all") && loc.equals("all")) {
			where = "";
		} else if (category.equals("all") && !loc.equals("all")) {
			where = "WHERE LOC = " + loc;
		} else if (!category.equals("all") && loc.equals("all")) {
			where = "WHERE CATEGORY = " + category;
		} else {
			where = "WHERE CATEGORY = " + category + "AND LOC = " + loc;
		}
		
		String sql = "SELECT * "
				   + "FROM (SELECT ROWNUM AS RNUM, B.* "
				   + "      FROM (SELECT * FROM BOARD "
				   + "            " + where + ""
				   + "            ORDER BY " + sort + ") B"
				   + ") "
				   + "WHERE RNUM >= ? AND RNUM <= ?";
		
		List<Board> list = new ArrayList<Board>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		
		System.out.println("startrow: " + startrow);
		System.out.println("endrow: " + endrow);
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			Board board = null;
			
			while (rs.next()) {
				board = new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setUser_id(rs.getString("user_id"));
				board.setTitle(textLengthOverCut(rs.getString("title"), 15));
				board.setBoard_img(rs.getString("board_img"));
				board.setCategory(rs.getInt("category"));
				board.setLoc(rs.getInt("loc"));
				board.setAddress(textLengthOverCut(rs.getString("address"), 10));
				board.setStart_date(rs.getString("start_date"));
				board.setEnd_date(rs.getString("end_date"));
				board.setHeart_count(rs.getInt("heart_count"));
				list.add(board);
			}
		} catch (Exception e) {
			System.out.println("getBoardList() exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생");
			}
		}
		return list;
	}

	// 게시글 개수(검색)
	public int getListCountBySearchBar(String keyword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		
		String sql = "SELECT COUNT(*) FROM BOARD "
				   + "WHERE TITLE LIKE '%" + keyword + "%'";
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getListCountBySearchBar() exception: " + e.getMessage());
			e.printStackTrace();
		} finally {	
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생");
			}
		}
		return total;
	}
	
	// 게시글 리스트 출력(검색)
	public List<Board> getBoardListBySearchBar(int page, int limit, String keyword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * "
				   + "FROM (SELECT ROWNUM AS RNUM, B.* "
				   + "      FROM (SELECT * FROM BOARD "
				   + "            WHERE TITLE LIKE '%" + keyword + "%'"
				   + "            ORDER BY BOARD_ID DESC) B"
				   + ") "
				   + "WHERE RNUM >= ? AND RNUM <= ?";
		
		List<Board> list = new ArrayList<Board>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		
		System.out.println("startrow: " + startrow);
		System.out.println("endrow: " + endrow);
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			Board board = null;
			
			while (rs.next()) {
				board = new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setUser_id(rs.getString("user_id"));
				board.setTitle(textLengthOverCut(rs.getString("title"), 15));
				board.setCategory(rs.getInt("category"));
				board.setLoc(rs.getInt("loc"));
				board.setAddress(textLengthOverCut(rs.getString("address"), 10));
				board.setStart_date(rs.getString("start_date"));
				board.setEnd_date(rs.getString("end_date"));
				board.setHeart_count(rs.getInt("heart_count"));
				list.add(board);
			}
		} catch (Exception e) {
			System.out.println("getBoardListBySearchBar() exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생");
			}
		}
		return list;
	}
	
	// 게시글 상세보기
	public Board getBoardDetail(int board_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM BOARD WHERE BOARD_ID = ?");
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				board = new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setUser_id(rs.getString("user_id"));
				board.setTitle(rs.getString("title"));
				board.setBoard_img(rs.getString("board_img"));
				board.setCategory(rs.getInt("category"));
				board.setLoc(rs.getInt("loc"));
				board.setHost(rs.getString("host"));
				board.setAddress(rs.getString("address"));
				board.setStart_time(rs.getString("start_time"));
				board.setEnd_time(rs.getString("end_time"));
				board.setStart_date(rs.getString("start_date"));
				board.setEnd_date(rs.getString("end_date"));
				board.setWrite_date(rs.getString("write_date").substring(0, 16));
				board.setContent(rs.getString("content"));
				board.setHeart_count(rs.getInt("heart_count"));;
			}
		} catch (Exception e) {
			System.out.println("getBoardDetail() exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생");
			}
		}
		return board;
	}
	
	// 게시글 삽입
	public boolean boardInsert(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();

			String max_sql = "(SELECT NVL(MAX(BOARD_ID),0)+1 FROM BOARD)";
			
			String sql = "INSERT INTO BOARD " 
					   + "(BOARD_ID, USER_ID, TITLE, BOARD_IMG, "
					   + "CATEGORY, LOC, HOST, ADDRESS, "
					   + "START_TIME, END_TIME, START_DATE, END_DATE, CONTENT) "
					   + "VALUES(" + max_sql+ ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getUser_id());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getBoard_img());
			pstmt.setInt(4, board.getCategory());
			pstmt.setInt(5, board.getLoc());
			pstmt.setString(6, board.getHost());
			pstmt.setString(7, board.getAddress());
			pstmt.setString(8, board.getStart_time());
			pstmt.setString(9, board.getEnd_time());
			pstmt.setString(10, board.getStart_date());
			pstmt.setString(11, board.getEnd_date());
			pstmt.setString(12, board.getContent());
			result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("데이터 삽입 완료.");
				return true;
			}
		} catch (Exception e) {
			System.out.println("boardInsert() exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return false;
	}
	
	// 게시글 수정
	public int boardUpdate(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			
			String sql = "UPDATE BOARD SET "
					   + "TITLE = ?, BOARD_IMG = ?, CATEGORY = ?, LOC = ?, HOST = ?, ADDRESS = ?, "
					   + "START_TIME = ?, END_TIME = ?, START_DATE = ?, END_DATE = ?, CONTENT = ? "
					   + "WHERE BOARD_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getBoard_img());
			pstmt.setInt(3, board.getCategory());
			pstmt.setInt(4, board.getLoc());
			pstmt.setString(5, board.getHost());
			pstmt.setString(6, board.getAddress());
			pstmt.setString(7, board.getStart_time());
			pstmt.setString(8, board.getEnd_time());
			pstmt.setString(9, board.getStart_date());
			pstmt.setString(10, board.getEnd_date());
			pstmt.setString(11, board.getContent());
			pstmt.setInt(12, board.getBoard_id());
			result = pstmt.executeUpdate();			
		} catch (Exception e) {
			System.out.println("boardUpdate() exception: " + e.getMessage());
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}
	
	// 게시글 삭제
	public int boardDelete(int board_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {	
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("DELETE BOARD WHERE BOARD_ID = ?");
			pstmt.setInt(1, board_id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardDelete() exception: " + e.getMessage());
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}
	
	// 회원이 작성한 게시글 불러오기
	public JsonArray getMyBoard(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JsonArray array = new JsonArray();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE USER_ID = ?");
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JsonObject object = new JsonObject();
				object.addProperty("board_id", rs.getInt("board_id"));
				object.addProperty("user_id", rs.getString("user_id"));
				object.addProperty("title", rs.getString("title"));
				object.addProperty("write_date", rs.getString("write_date"));
				object.addProperty("heart_count", rs.getInt("heart_count"));
				array.add(object);
			}
		} catch (Exception e) {
			System.out.println("getMyBoard() exception: " + e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return array;
	}
	
}