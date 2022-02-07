package my.mypage.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
private DataSource ds;
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch (Exception e) {
			System.out.println("DB연결 실패:" + e);
		}
	}
	
	public Member getUser(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		
		try {

			conn = ds.getConnection();
			
			String sql = "select * from member where id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				m = new Member();
				m.setId(rs.getString("id"));
				m.setPassword(rs.getString("password"));				
				m.setEmail(rs.getString("email"));
				m.setName(rs.getString("name"));
				m.setProfile_img(rs.getString("profile_img"));
				m.setReg_date(rs.getString("reg_date"));
			}
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//여는 순서와 반대로 닫기
			try {
				if(rs != null)
					rs.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(conn != null)
					conn.close();	//4단계 : DB연결을 끊는다.
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		return m;
	}
	
	
	public int updateImg(String profile, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			
			String sql = "update member set profile_img = ? where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, profile);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//여는 순서와 반대로 닫기
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(conn != null)
					conn.close();	//4단계 : DB연결을 끊는다.
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return result;
	}
	
	public int updateInfo(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			
			String sql = "update member set password = ?, email = ?, "
					+ "name= ? where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getId());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//여는 순서와 반대로 닫기
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(conn != null)
					conn.close();	//4단계 : DB연결을 끊는다.
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return result;
	}
}
