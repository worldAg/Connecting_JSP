package bo.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
	
	// 회원의 해당 게시글 관심 등록 여부 조회
	public String getMemberHeartForBoard(String user_id, String board_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		String sql = "SELECT * FROM USER_HEART " +
					 "WHERE USER_ID = '" + user_id + "' AND BOARD_ID = '" + board_id + "'";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = "true";
			} else {
				result = "false";
			}
		} catch (Exception e) {
			System.out.println("getMemberHeartForBoard() exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println("ResultSet 닫는 중 예외 발생");
			} try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				System.out.println("PreparedStatement 닫는 중 예외 발생");
			} try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("Connection 닫는 중 예외 발생");
			}
		}
		return result;
	}
	
	// 회원의 관심글 조회
	public JsonArray getMyHeartList(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JsonArray array = new JsonArray();
		String sql = "SELECT BOARD_ID FROM USER_HEART WHERE USER_ID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JsonObject object = new JsonObject();
				object.addProperty("board_id", rs.getInt("board_id"));
				array.add(object);
			}
		} catch (Exception e) {
			System.out.println("getMyHeartList() exception: " + e.getMessage());
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
	
	
	/* 관심(하트) 등록 */
	// 관심 등록 시 USER_HEART 데이터 추가
	public String addHeartFromMember(String user_id, String board_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "false";	
		try {
			con = ds.getConnection();
			
			String sql = "INSERT INTO " +
						 "USER_HEART (USER_ID, BOARD_ID) VALUES(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, Integer.parseInt(board_id));
			if (pstmt.executeUpdate() == 1) {
				result = "true";
			}
		} catch (Exception e) {
			System.out.println("addHeartFromMember() exception: " + e.getMessage());
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}
	
	// 관심 등록 시 BOARD의 heart_count 1 증가
	public String increadeHeartNum(String boardId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "false";
		try {
			con = ds.getConnection();
			
			// BOARD 테이블에서 heart_count 조회
			String selectSQL = "SELECT HEART_COUNT "
							 + "FROM BOARD " 
							 + "WHERE BOARD_ID = '" + boardId + "'";
			pstmt = con.prepareStatement(selectSQL);			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int heartNum = rs.getInt(1);
				int increasedHeartNum = heartNum + 1; // heart_count 값 증가(+1)
				
				// 증가시킨 heart_count 적용
				String updateSQL = "UPDATE BOARD " + 
				                   "SET HEART_COUNT = " + (increasedHeartNum) + 
				                   "WHERE BOARD_ID = '" + boardId + "'";
				
				if (pstmt != null) // 조회된 값이 없을 경우
					pstmt.close();
				
				pstmt = con.prepareStatement(updateSQL);
				if (pstmt.executeUpdate() == 1) {
					result = "true";
				}
			}
		} catch (Exception e) {
			System.out.println("increadeHeartNum() exception: " + e.getMessage());
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
		return result;
	}
	
	
	/* 관심(하트) 취소 */
	// 관심 취소 시 USER_HEART 데이터 삭제
	public String removeHeartFromMember(String userId, String boardId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "false";
		try {
			con = ds.getConnection();
			
			String sql = "DELETE FROM USER_HEART " +
						 "WHERE USER_ID = '" + userId + "'AND BOARD_ID = '" + boardId + "'";
			
			pstmt = con.prepareStatement(sql);
			if (pstmt.executeUpdate() == 1) 
				result = "true";
		} catch (Exception e) {
			System.out.println("removeHeartFromMember() exception: " + e.getMessage());
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}
	
	// 관심 취소 시 BOARD의 heart_count 1 감소
	public String decreaseHeartNum(String boardId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "false";
		try {
			con = ds.getConnection();
			
			// BOARD 테이블에서 heart_count 조회
			String selectSQL = "SELECT HEART_COUNT "
							 + "FROM BOARD " 
							 + "WHERE BOARD_ID = '" + boardId + "'";
			pstmt = con.prepareStatement(selectSQL);			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int heartNum = rs.getInt(1);
				int decreasedHeartNum = heartNum - 1; // heart_count 값 증가(-1)
				
				// 감소시킨 heart_count 적용
				String updateSQL = "UPDATE BOARD " + 
				                   "SET HEART_COUNT = " + (decreasedHeartNum) + 
				                   "WHERE BOARD_ID = '" + boardId + "'";
				
				if (pstmt != null) // 조회된 값이 없을 경우
					pstmt.close();
				
				pstmt = con.prepareStatement(updateSQL);
				if (pstmt.executeUpdate() == 1) {
					result = "true";
				}
			}
		} catch (Exception e) {
			System.out.println("decreaseHeartNum() exception: " + e.getMessage());
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
		return result;
	}

}
