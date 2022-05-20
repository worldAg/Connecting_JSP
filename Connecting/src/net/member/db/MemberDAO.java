package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	private DataSource ds;
	
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("Oracle DB 연결 실패: " + e.getMessage());
		}
	}

	// 아이디 존재 여부 확인
	public int isId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1; // DB에 해당 id 없음
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("SELECT ID FROM MEMBER WHERE ID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = 0; // DB에 해당 id가 있음
			}
		} catch (Exception e) {
			e.printStackTrace();
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
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}
	
	// 아이디와 비밀번호 조회(로그인 시 사용) 
	public int isId(String id, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		int result = -1; // DB에 해당 id가 없음
		try {
			conn = ds.getConnection();
			
			String sql = "SELECT ID, PASSWORD FROM MEMBER WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString(2).equals(pass)) {
					result = 1;
				} else {
					result = 0; // DB에 해당 id가 있음
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}

	
	
	
	
 public int insert(Member m) {
	 Connection con=null;
	 PreparedStatement pstmt=null;
	 int result=0;
	 try {
		 	con = ds.getConnection();
		 	System.out.println("getConnection : insert()");
		 	
		 	pstmt = con.prepareStatement(
		 			"INSERT INTO MEMBER (id, password, name, email) VALUES (?,?,?,?)");
		 	pstmt.setString(1, m.getID());
		 	pstmt.setString(2, m.getPASSWORD());
		 	pstmt.setString(3, m.getNAME());
		 	pstmt.setString(4, m.getEMAIL());
		 	result = pstmt.executeUpdate(); //삽입 성공시 result는 1
	 }catch(java.sql.SQLIntegrityConstraintViolationException e) {
		 result = -1;
		 System.out.println("멤버 아이디 중복 에러입니다.");
	 }catch (Exception e) {
		 e.printStackTrace();
		}finally {
			if (pstmt != null)
				try {
					pstmt.close();
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
		}//finally
		return result;
	}//insert end

public String searchID(String email, String name) {
	Connection con=null;
	 PreparedStatement pstmt=null;
	 ResultSet rs=null;
	 try {
		 con = ds.getConnection();
		 
		 String sql = "select id from member where email = ? and name = ?";
		 pstmt = con.prepareStatement(sql);
		 pstmt.setString(1, email);
		 pstmt.setString(2, name);
		 rs = pstmt.executeQuery();
		 
		 if(rs.next()){
			 return rs.getString(1);
		 }
	 } catch (Exception e) {
		 e.printStackTrace();
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
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
				if (con != null)
					try {
						con.close();
					} catch(SQLException ex) {
						ex.printStackTrace();
					}
			}//finally
	 return null;
		}//searchid end
public String searchPW(String name , String id, String email) {
	Connection con=null;
	 PreparedStatement pstmt=null;
	 ResultSet rs=null;
	 try {
		 con = ds.getConnection();
		 
		 String sql = "select password from member where name=? and id = ? and email = ?";
		 pstmt = con.prepareStatement(sql);
		 pstmt.setString(1, name);
		 pstmt.setString(2, id);
		 pstmt.setString(3, email);
		 rs = pstmt.executeQuery();
		 
		 if(rs.next()){
			 return rs.getString(1);
		 }
	 } catch (Exception e) {
		 e.printStackTrace();
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
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
				if (con != null)
					try {
						con.close();
					} catch(SQLException ex) {
						ex.printStackTrace();
					}
			}//finally
	 return null;
		}//searchPW end
}
