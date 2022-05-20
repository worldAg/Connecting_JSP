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
	
	// 회원이 해당 게시글을 관심 등록했는지 조회
	public String getMemberHeartForBoard(String user_id, String board_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		String sql = "SELECT * FROM USER_HEART "
						 + "WHERE USER_ID = '" + user_id + "' AND BOARD_ID = '" + board_id + "'";
		
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
	
	// 회원이 하트 버튼을 클릭(관심 등록)
	public String addHeartFromMember(String user_id, String board_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "false";
		
		// 일단, user_heart 테이블에 userId와 boardId가 서로 일치하는 row가 있는지 확인하자.
		// 만약, select 문을 사용하여 조회했을 때 존재한다면, 추가할 필요가 없고, 존재하지 않는다면 insert문을 사용하여 추가하자.
		
		String selectSQL = "SELECT * FROM USER_HEART "
						 + "WHERE USER_ID = '" + user_id + "' AND BOARD_ID = '" + board_id + "'";
		
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
				pstmt.setString(1, user_id);
				pstmt.setInt(2, Integer.parseInt(board_id.trim()));
				
				if (pstmt.executeUpdate() == 1) {
					result = "true";
				}
			}
		} catch (Exception e) {
			System.out.println("addHeartFromMember() exception: " + e.getMessage());
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

	
	

	
	
}
