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

public class HeartDAO {

	private DataSource ds;

	public HeartDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("Oracle DB 연결 실패: " + e.getMessage());
		}
	}
	
	private String textLengthOverCut(String original) {
		if (original.length() >= 12) {
			return original.substring(0, 12) + "...";
		}
		else return original;
	}
	
	
	
	// 하연님
	public String[] getHeartBoardId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] bo_Idlist = null;
		
		try {
			String heart_sql = " select board_id from user_heart where user_id = ? ";
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(heart_sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			String bo_Id = "";
			
			while(rs.next()) {
				bo_Id += rs.getString("board_id") + ",";
			}
			
			bo_Idlist = bo_Id.split(",");			

			
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		}

		return bo_Idlist;
	}
	
	
	// 하연님
	public List<Board> getHeartBoardList(String id,int page,int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boardlist = new ArrayList<Board>();
		String[] bo_Idlist = getHeartBoardId(id);
		
		try {
			String board_sql = " select * from board where board_id = ? ";
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_sql);
			
			int startrow = (page -1) * limit + 1;
			int endrow = startrow + limit -1;
			
			if(endrow > bo_Idlist.length) {
				endrow = bo_Idlist.length;
			}
			
			for(int i = startrow-1; i < endrow; i++) {
				int j = Integer.parseInt(bo_Idlist[i]);
				pstmt.setInt(1, j);
				rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					Board board = new Board();
					board.setBoard_id(rs.getInt("board_id"));
					board.setCategory(rs.getInt("category"));
					board.setLoc(rs.getInt("loc"));
					board.setId(rs.getString("id"));
					board.setTitle(rs.getString("title"));
					board.setHost_name(rs.getString("host_name"));
					board.setAddress(rs.getString("address"));
					board.setStart_date(rs.getString("start_date"));
					board.setEnd_date(rs.getString("end_date"));
					board.setStart_time(rs.getString("start_time"));
					board.setEnd_time(rs.getString("end_time"));
					board.setWrite_date(rs.getString("write_date"));
					board.setContent(rs.getString("content"));
					board.setBoard_img(rs.getString("board_img"));
					boardlist.add(board);
				}else {
					endrow += 1;
					if(endrow > bo_Idlist.length) {
						endrow = bo_Idlist.length;
					}
				}
			}
			
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		}
		return boardlist;
		
	}
	
	
	// 하연님
	public int getMyListCount(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			String heart_sql = " select board_id from user_heart where user_id = ? ";
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(heart_sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			String bo_Id = "";
			
			while(rs.next()) {
				bo_Id += rs.getString("board_id") + ",";
			}
			
			String[] bo_Idlist = bo_Id.split(",");			
			x = bo_Idlist.length;
		}catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		}
		return x;
	}
	
	// 하연님
	public Board getBoardById(int board_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = new Board();
		
		try {
			
			String sql = "select * from board where board_id = ? ";
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {				
				board.setBoard_id(rs.getInt("board_id"));
				board.setCategory(rs.getInt("category"));
				board.setLoc(rs.getInt("loc"));
				board.setId(rs.getString("id"));
				board.setTitle(rs.getString("title"));
				board.setHost_name(rs.getString("host_name"));
				board.setAddress(rs.getString("address"));
				board.setStart_date(rs.getString("start_date"));
				board.setEnd_date(rs.getString("end_date"));
				board.setStart_time(rs.getString("start_time"));
				board.setEnd_time(rs.getString("end_time"));
				board.setWrite_date(rs.getString("write_date"));
				board.setContent(rs.getString("content"));
				board.setBoard_img(rs.getString("board_img"));
			}
			
		}catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		}
		
		return board;
	}
	
	// 하연님
	public int boardUpdate(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			
			String sql = "update board set "
						 + "category = ?, loc = ?,"
						 + "board_img = ?, title = ?, host_name = ?,"
						 + "address = ?, start_date = ?, end_date = ?,"
						 + "start_time = ?, end_time = ?, content=? "
						 + "where board_id = ?";
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getCategory());
			pstmt.setInt(2, board.getLoc());
			pstmt.setString(3, board.getBoard_img());
			pstmt.setString(4, board.getTitle());
			pstmt.setString(5, board.getHost_name());
			pstmt.setString(6, board.getAddress());
			pstmt.setString(7, board.getStart_date());
			pstmt.setString(8, board.getEnd_date());
			pstmt.setString(9, board.getStart_time());
			pstmt.setString(10, board.getEnd_time());
			pstmt.setString(11, board.getContent());
			pstmt.setInt(12, board.getBoard_id());
			
			
			result = pstmt.executeUpdate();
			
			
		}catch (Exception ex) {
			System.out.println("updateBoard() 에러: " + ex);
		} finally {
	
			if (pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}
	
	// 하연님
	public int deleteBoard(int board_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "delete board where board_id = ? ";
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			result = pstmt.executeUpdate();
		}catch (Exception ex) {
			System.out.println("updateBoard() 에러: " + ex);
		} finally {
	
			if (pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}
	
	
	// 세은님
	public boolean boardInsert(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();

			String max_sql = "(SELECT NVL(MAX(BOARD_ID),0)+1 FROM BOARD)";
			
			String sql = "INSERT INTO BOARD " + "(BOARD_ID, CATEGORY, LOC, "
					+ " ID, TITLE, HOST_NAME, ADDRESS, START_DATE, END_DATE, START_TIME, END_TIME, "
					+ " CONTENT, BOARD_IMG) VALUES(" + max_sql+ ", ?, ?, ?, ?, ?, ?, "
					+ " ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getCategory());
			pstmt.setInt(2, board.getLoc());
			pstmt.setString(3, board.getId());
			pstmt.setString(4, board.getTitle());
			pstmt.setString(5, board.getHost_name());
			pstmt.setString(6, board.getAddress());
			pstmt.setString(7, board.getStart_date());
			pstmt.setString(8, board.getEnd_date());
			pstmt.setString(9, board.getStart_time());
			pstmt.setString(10, board.getEnd_time());
			pstmt.setString(11, board.getContent());
			pstmt.setString(12, board.getBoard_img());
			result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
				return true;
			}

		} catch (Exception e) {
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


	public List<Board> getBoardListOrderByDefault(int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		/*
		 * page: 현재 페이지
		 * limit: 한 페이지당 최대 개시글 수
		 * BOARD_RE_REF DESC, BOARD_RE_SEQ ASC 에 의해 정렬된 것을,
		 * 조건절에 맞는 RNUM의 범위 만큼 가져오는 쿼리문
		 */
		
		String selectSQL = "SELECT * " +
		                   "FROM (SELECT ROWNUM RNUM, J.* " +
		                   "      FROM (SELECT BOARD.*, NVL(HEART_NUM, 0) HEART_NUM " +
		                   "            FROM BOARD LEFT OUTER JOIN BOARD_HEART " +
		                   "            ON BOARD.BOARD_ID = BOARD_HEART.BOARD_ID " + 
		                   "            ORDER BY BOARD.BOARD_ID DESC) J " + 
		                   "      )" + 
		                   "WHERE RNUM >= ? AND RNUM <= ?";
		
		
		List<Board> list = new ArrayList<>();
		
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		
		System.out.println("startrow: " + startrow);
		System.out.println("endrow: " + endrow);
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			Board board = null;
			
			while (rs.next()) {
				board = new Board();
				
				board.setBoard_id(rs.getInt("BOARD_ID"));
				board.setCategory(rs.getInt("CATEGORY"));
				board.setLoc(rs.getInt("LOC"));
				board.setId(rs.getString("ID"));
				board.setTitle(textLengthOverCut(rs.getString("TITLE")));
				board.setHost_name(rs.getString("HOST_NAME"));
				board.setAddress(rs.getString("ADDRESS"));
				board.setStart_date(rs.getString("START_DATE"));
				board.setEnd_date(rs.getString("END_DATE"));
				board.setStart_time(rs.getString("START_TIME"));
				board.setEnd_time(rs.getString("END_TIME"));
				board.setWrite_date(rs.getString("WRITE_DATE").substring(0, 10));
				board.setContent(rs.getString("CONTENT"));
				board.setBoard_img(rs.getString("BOARD_IMG"));
				
				board.setHeart_num(rs.getInt("HEART_NUM"));
				
				list.add(board);
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - getBoardList(int page, int limit) exception - " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}
		return list;
	}

	public List<Board> getBoardListOrderByHeart(int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		/*
		 * page: 현재 페이지
		 * limit: 한 페이지당 최대 개시글 수
		 * BOARD_RE_REF DESC, BOARD_RE_SEQ ASC 에 의해 정렬된 것을,
		 * 조건절에 맞는 RNUM의 범위 만큼 가져오는 쿼리문
		 */
		
		String selectSQL = "SELECT * " +
		                   "FROM (SELECT ROWNUM RNUM, J.* " +
		                   "      FROM (SELECT BOARD.*, NVL(HEART_NUM, 0) HEART_NUM " +
		                   "            FROM BOARD LEFT OUTER JOIN BOARD_HEART " +
		                   "            ON BOARD.BOARD_ID = BOARD_HEART.BOARD_ID " + 
		                   "            ORDER BY HEART_NUM DESC, BOARD.BOARD_ID DESC) J " + 
		                   "      )" + 
		                   "WHERE RNUM >= ? AND RNUM <= ?";
		
		
		List<Board> list = new ArrayList<>();
		
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		
		System.out.println("startrow: " + startrow);
		System.out.println("endrow: " + endrow);
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			Board board = null;
			
			while (rs.next()) {
				board = new Board();
				
				board.setBoard_id(rs.getInt("BOARD_ID"));
				board.setCategory(rs.getInt("CATEGORY"));
				board.setLoc(rs.getInt("LOC"));
				board.setId(rs.getString("ID"));
				board.setTitle(textLengthOverCut(rs.getString("TITLE")));
				board.setHost_name(rs.getString("HOST_NAME"));
				board.setAddress(rs.getString("ADDRESS"));
				board.setStart_date(rs.getString("START_DATE"));
				board.setEnd_date(rs.getString("END_DATE"));
				board.setStart_time(rs.getString("START_TIME"));
				board.setEnd_time(rs.getString("END_TIME"));
				board.setWrite_date(rs.getString("WRITE_DATE").substring(0, 10));
				board.setContent(rs.getString("CONTENT"));
				board.setBoard_img(rs.getString("BOARD_IMG"));
				
				board.setHeart_num(rs.getInt("HEART_NUM"));
				
				list.add(board);
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - getBoardList(int page, int limit) exception - " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}
		return list;
	}

	public List<Board> getBoardListOrderByStartDate(int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		/*
		 * page: 현재 페이지
		 * limit: 한 페이지당 최대 개시글 수
		 * BOARD_RE_REF DESC, BOARD_RE_SEQ ASC 에 의해 정렬된 것을,
		 * 조건절에 맞는 RNUM의 범위 만큼 가져오는 쿼리문
		 */
		
		String selectSQL = "SELECT * " +
		                   "FROM (SELECT ROWNUM RNUM, J.* " +
		                   "      FROM (SELECT BOARD.*, NVL(HEART_NUM, 0) HEART_NUM " +
		                   "            FROM BOARD LEFT OUTER JOIN BOARD_HEART " +
		                   "            ON BOARD.BOARD_ID = BOARD_HEART.BOARD_ID " + 
		                   "            ORDER BY START_DATE ASC, BOARD.BOARD_ID DESC) J " + 
		                   "      )" + 
		                   "WHERE RNUM >= ? AND RNUM <= ?";
		
		
		List<Board> list = new ArrayList<>();
		
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		
		System.out.println("startrow: " + startrow);
		System.out.println("endrow: " + endrow);
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			Board board = null;
			
			while (rs.next()) {
				board = new Board();
				
				board.setBoard_id(rs.getInt("BOARD_ID"));
				board.setCategory(rs.getInt("CATEGORY"));
				board.setLoc(rs.getInt("LOC"));
				board.setId(rs.getString("ID"));
				board.setTitle(textLengthOverCut(rs.getString("TITLE")));
				board.setHost_name(rs.getString("HOST_NAME"));
				board.setAddress(rs.getString("ADDRESS"));
				board.setStart_date(rs.getString("START_DATE"));
				board.setEnd_date(rs.getString("END_DATE"));
				board.setStart_time(rs.getString("START_TIME"));
				board.setEnd_time(rs.getString("END_TIME"));
				board.setWrite_date(rs.getString("WRITE_DATE").substring(0, 10));
				board.setContent(rs.getString("CONTENT"));
				board.setBoard_img(rs.getString("BOARD_IMG"));
				
				board.setHeart_num(rs.getInt("HEART_NUM"));
				
				list.add(board);
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - getBoardList(int page, int limit) exception - " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}
		return list;
	}

	public List<Board> getBoardListOrderByEndDate(int page, int limit) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		/*
		 * page: 현재 페이지
		 * limit: 한 페이지당 최대 개시글 수
		 * BOARD_RE_REF DESC, BOARD_RE_SEQ ASC 에 의해 정렬된 것을,
		 * 조건절에 맞는 RNUM의 범위 만큼 가져오는 쿼리문
		 */
		
		String selectSQL = "SELECT * " +
		                   "FROM (SELECT ROWNUM RNUM, J.* " +
		                   "      FROM (SELECT BOARD.*, NVL(HEART_NUM, 0) HEART_NUM " +
		                   "            FROM BOARD LEFT OUTER JOIN BOARD_HEART " +
		                   "            ON BOARD.BOARD_ID = BOARD_HEART.BOARD_ID " + 
		                   "            ORDER BY END_DATE ASC, BOARD.BOARD_ID DESC) J " + 
		                   "      )" + 
		                   "WHERE RNUM >= ? AND RNUM <= ?";
		
		
		List<Board> list = new ArrayList<>();
		
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		
		System.out.println("startrow: " + startrow);
		System.out.println("endrow: " + endrow);
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			Board board = null;
			
			while (rs.next()) {
				board = new Board();
				
				board.setBoard_id(rs.getInt("BOARD_ID"));
				board.setCategory(rs.getInt("CATEGORY"));
				board.setLoc(rs.getInt("LOC"));
				board.setId(rs.getString("ID"));
				board.setTitle(textLengthOverCut(rs.getString("TITLE")));
				board.setHost_name(rs.getString("HOST_NAME"));
				board.setAddress(rs.getString("ADDRESS"));
				board.setStart_date(rs.getString("START_DATE"));
				board.setEnd_date(rs.getString("END_DATE"));
				board.setStart_time(rs.getString("START_TIME"));
				board.setEnd_time(rs.getString("END_TIME"));
				board.setWrite_date(rs.getString("WRITE_DATE").substring(0, 10));
				board.setContent(rs.getString("CONTENT"));
				board.setBoard_img(rs.getString("BOARD_IMG"));
				
				board.setHeart_num(rs.getInt("HEART_NUM"));
				
				list.add(board);
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - getBoardList(int page, int limit) exception - " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}
		return list;
	}

	public Board getDetail(int boardId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		
		String selectSQL = "SELECT * " +
		                   "FROM BOARD " +
				           "WHERE BOARD_ID = ?";		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				board = new Board();
				
				board.setBoard_id(rs.getInt("BOARD_ID"));
				board.setCategory(rs.getInt("CATEGORY"));
				board.setLoc(rs.getInt("LOC"));
				board.setId(rs.getString("ID"));
				board.setTitle(rs.getString("TITLE"));
				board.setHost_name(rs.getString("HOST_NAME"));
				board.setAddress(rs.getString("ADDRESS"));
				board.setStart_date(rs.getString("START_DATE"));
				board.setEnd_date(rs.getString("END_DATE"));
				board.setStart_time(rs.getString("START_TIME"));
				board.setEnd_time(rs.getString("END_TIME"));
				board.setWrite_date(rs.getString("WRITE_DATE").substring(0, 10));
				board.setContent(rs.getString("CONTENT"));
				board.setBoard_img(rs.getString("BOARD_IMG"));
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - getDetail(int boardId) exception - " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}
		return board;
	}

	public int getListCountByCategory(String category) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalListNum = 0;
		
		String selectSQL = "SELECT COUNT(*) " +
                		   "FROM BOARD " +
		                   "WHERE CATEGORY = ?";		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, Integer.parseInt(category.trim()));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				totalListNum = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - getListCountByCategory(String category) - " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}
		return totalListNum;
	}
	
	// 정렬 방식에 따른 
	public List<Board> getBoardListByCategoryOrderBy(int page, int limit, String category, String orderby) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Board board = null;
		List<Board> list = new ArrayList<>();
		
		String query = null;
		
		if (orderby.equals("0")) {
			query = " BOARD.BOARD_ID DESC";
		} else if (orderby.equals("1")) {
			query = " HEART_NUM DESC, BOARD.BOARD_ID DESC";
		} else if (orderby.equals("2")) {
			query = " START_DATE ASC, BOARD.BOARD_ID DESC";
		} else if (orderby.equals("3")) {
			query = " END_DATE ASC, BOARD.BOARD_ID DESC";
		}
		
		String selectSQL = "SELECT * " +
		                   "FROM (SELECT ROWNUM RNUM, J.* " +
		                   "      FROM (SELECT BOARD.*, NVL(HEART_NUM, 0) HEART_NUM " +
		                   "            FROM BOARD LEFT OUTER JOIN BOARD_HEART " +
		                   "            ON BOARD.BOARD_ID = BOARD_HEART.BOARD_ID " +
		                   "            WHERE CATEGORY = ? " +
		                   "            ORDER BY" + query + ") J " + 
		                   "      )" + 
		                   "WHERE RNUM >= ? AND RNUM <= ?";	
		
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		
		System.out.println("startrow: " + startrow);
		System.out.println("endrow: " + endrow);
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, Integer.parseInt(category.trim()));
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				board = new Board();
				
				board.setBoard_id(rs.getInt("BOARD_ID"));
				board.setCategory(rs.getInt("CATEGORY"));
				board.setLoc(rs.getInt("LOC"));
				board.setId(rs.getString("ID"));
				board.setTitle(textLengthOverCut(rs.getString("TITLE")));
				board.setHost_name(rs.getString("HOST_NAME"));
				board.setAddress(rs.getString("ADDRESS"));
				board.setStart_date(rs.getString("START_DATE"));
				board.setEnd_date(rs.getString("END_DATE"));
				board.setStart_time(rs.getString("START_TIME"));
				board.setEnd_time(rs.getString("END_TIME"));
				board.setWrite_date(rs.getString("WRITE_DATE").substring(0, 10));
				board.setContent(rs.getString("CONTENT"));
				board.setBoard_img(rs.getString("BOARD_IMG"));
				
				board.setHeart_num(rs.getInt("HEART_NUM"));
				
				list.add(board);
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - getBoardListByCategory(int page, int limit, String category) - " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}
		return list;
	}

	

	

	

	
	
	public String isAddedToMemberTable(String userId, String boardId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		String selectSQL = "SELECT * " +
		                   "FROM USER_HEART " +
		                   "WHERE USER_ID = '" + userId + "' AND BOARD_ID = '" + boardId + "'";
		
		try {
			con = ds.getConnection();			
			pstmt = con.prepareStatement(selectSQL);			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = "true";				
			} else {
				result = "false";
			}			
			System.out.println("Heart 테이블에 관심글로 등록여부: " + result);
		} catch (Exception e) {
			System.out.println("BoardDAO - isAddedToMemberTable(String userId, String boardId) exception - " + e.getMessage());
			e.printStackTrace();
		} finally {	
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}
		return result;
	}

	public String removeHeartFromMember(String userId, String boardId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "false";
		
		
		// 일단, user_heart 테이블에 userId와 boardId가 서로 일치하는 row가 있는지 확인하자.
		// 만약, select 문을 사용하여 조회했을 때 존재한다면, user_heart 테이블에서 delete 사용하여 제거하자.
		String selectSQL = "SELECT * " + 
		                   "FROM USER_HEART " +
				           "WHERE USER_ID = '" + userId + "' AND BOARD_ID = '" + boardId + "'";
		
		try {
			con = ds.getConnection();			
			pstmt = con.prepareStatement(selectSQL);			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String deleteSQL = "DELETE " +
			                       "FROM USER_HEART " +
			                       "WHERE USER_ID = '" + userId + "' AND BOARD_ID = '" + boardId + "'";
				
				if (pstmt != null) pstmt.close();
				pstmt = con.prepareStatement(deleteSQL);
				if (pstmt.executeUpdate() == 1) result = "true";
			} else {
				// user_heart 테이블에 존재하지 않는다면, 삭제할 필요도 없으니 그냥 바로 "true"를 return 하자.
				result = "true";
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - isAddedToMemberTable(String userId, String boardId) exception - " + e.getMessage());
			e.printStackTrace();
		} finally {	
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}		
		return result;
	}

	public String decreaseHeartNum(String boardId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "false";
		
		// board_heart 테이블에 boardId에 해당하는 row 가 있는지 확인하고, 있다면 1을 감소시키자.
		
		String selectSQL = "SELECT HEART_NUM " +
		                   "FROM BOARD_HEART " +
				           "WHERE BOARD_ID = '" + boardId + "'";
		
		try {
			con = ds.getConnection();			
			pstmt = con.prepareStatement(selectSQL);			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int heartNum = rs.getInt(1);
				int decreasedHeartNum = heartNum - 1;
				
				if (decreasedHeartNum < 0) decreasedHeartNum = 0;
				// 하트 수를 1 감소시키기 위해 update 문을 실행하자.
				String updateSQL = "UPDATE BOARD_HEART " + 
				                   "SET HEART_NUM = " + (decreasedHeartNum) + 
				                   "WHERE BOARD_ID = '" + boardId + "'";
				
				if (pstmt != null) pstmt.close();
				pstmt = con.prepareStatement(updateSQL);
				
				if (pstmt.executeUpdate() == 1) {
					result = "true";
				}
			} else {
				// 만약 board_heart 테이블에 boardId에 해당하는 게시물이 없을 경우, 1을 감소시킬 필요도 없기 때문에 그냥 "true"를 return하자...
				result = "true";
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - decreaseHeartNum(String boardId) exception - " + e.getMessage());
			e.printStackTrace();
		} finally {	
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}		
		return result;
	}

	public String addHeartFromMember(String userId, String boardId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "false";
		
		// 일단, user_heart 테이블에 userId와 boardId가 서로 일치하는 row가 있는지 확인하자.
		// 만약, select 문을 사용하여 조회했을 때 존재한다면, 추가할 필요가 없고, 존재하지 않는다면 insert문을 사용하여 추가하자.
		
		String selectSQL = "SELECT * " +
		                   "FROM USER_HEART " +
		                   "WHERE USER_ID = '" + userId + "' AND BOARD_ID = '" + boardId + "'";
		
		try {
			con = ds.getConnection();			
			pstmt = con.prepareStatement(selectSQL);			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// 존재한다면 추가할 필요가 없기 때문에 result에 바로 true
				result = "true";
			} else {
				// user_heart 테이블에 존재하지 않는다면, 추가하자.
				String insertSQL = "INSERT " +
				                   "INTO USER_HEART " +
						           "VALUES(?, ?)";
				
				if (pstmt != null) pstmt.close();
				
				pstmt = con.prepareStatement(insertSQL);
				pstmt.setString(1, userId);
				pstmt.setInt(2, Integer.parseInt(boardId.trim()));
				
				if (pstmt.executeUpdate() == 1) {
					result = "true";
				}
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - addHeartFromMember(String userId, String boardId) - " + e.getMessage());
			e.printStackTrace();
		} finally {	
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}		
		return result;
	}

	public String increadeHeartNum(String boardId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "false";
		
		// board_heart 테이블에 boardId에 해당하는 row 가 있는지 확인하고, 있다면 1을 증가시키자.
		
		String selectSQL = "SELECT HEART_NUM " +
		                   "FROM BOARD_HEART " +
				           "WHERE BOARD_ID = '" + boardId + "'";
		
		try {
			con = ds.getConnection();			
			pstmt = con.prepareStatement(selectSQL);			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int heartNum = rs.getInt(1);
				int increasedHeartNum = heartNum + 1;
				
				// 하트 수를 1 증가시키기 위해 update 문을 실행하자.
				String updateSQL = "UPDATE BOARD_HEART " + 
				                   "SET HEART_NUM = " + (increasedHeartNum) + 
				                   "WHERE BOARD_ID = '" + boardId + "'";
				
				if (pstmt != null) pstmt.close();
				pstmt = con.prepareStatement(updateSQL);
				
				if (pstmt.executeUpdate() == 1) {
					result = "true";
				}
			} else {
				// 만약, board_heart 테이블에 존재하지 않는다면 새롭게 추가해줘야한다. 그러면 "좋아요"의 숫자는 1이 될 것이다.
				String insertSQL = "INSERT " +
				                   "INTO BOARD_HEART " +
						           "VALUES(?, ?)";
				
				if (pstmt != null) pstmt.close();
				pstmt = con.prepareStatement(insertSQL);
				pstmt.setInt(1, Integer.parseInt(boardId.trim()));
				pstmt.setInt(2, 1);
				
				if (pstmt.executeUpdate() == 1) {
					result = "true";
				}
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - increadeHeartNum(String boardId) exception - " + e.getMessage());
			e.printStackTrace();
		} finally {	
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}		
		return result;
	}

	public List<Board> getRecentBoard() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectSQL = "select * "
				         + "from "
				         + "(select rownum rnum, b1.* "
				         + "from (select * "
				         + "      from board " 
				         + "      order by write_date desc) b1) "
				         + "      where rnum >= 1 and rnum <= 6";
		List<Board> list = new ArrayList<>();

		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			
			rs = pstmt.executeQuery();
			Board board = null;
			
			while (rs.next()) {
				board = new Board();
				
				board.setBoard_id(rs.getInt("BOARD_ID"));
				board.setTitle(rs.getString("TITLE"));
				board.setAddress(rs.getString("ADDRESS"));
				board.setStart_date(rs.getString("START_DATE"));
				board.setEnd_date(rs.getString("END_DATE"));
				board.setBoard_img(rs.getString("BOARD_IMG"));
				
				list.add(board);
			}
		} catch (Exception e) {
			System.out.println("BoardDAO - getRecentBoard() exception - " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생.");
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생.");
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생.");
			}
		}
		return list;
	}	
	
} // whole BoardDAO class ends
