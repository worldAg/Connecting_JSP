/*
 DAO(Data Access Object) 클래스
 -데이터 베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스 입니다.
 -어떤 Action클래스가 호출되더라도 그에 해당하는 데이터 베이스 연동 처리는
 	DAO클래스에서 이루어지게 됩니다.
 */

package my.mypage.db;


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
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch (Exception e) {
			System.out.println("DB연결 실패:" + e);
		}
	}

	
	
	public List<Board> getMyBoard(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boardlist = new ArrayList<Board>();
		
		try {
			String sql = "select * "
					+ "from board where id = ?";
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);;
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
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
}
