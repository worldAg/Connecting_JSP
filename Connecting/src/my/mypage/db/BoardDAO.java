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
	

	
	
	
	
	
	
}
