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
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public MemberDAO() {
		try {
				Context init = new InitialContext();
				ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch (Exception ex) {
			System.out.println("DB_연결 실패 :" + ex);
		}
	}
	public int isId(String id) {
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=-1;//DB에 해당id가 없습니다.
		try {
			con = ds.getConnection();
			
			String sql = "select id from member where id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = 0; //DB에 해당 id가 있습니다.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null)
				try {
					rs.close();
				}catch (SQLException ex) {
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
		return result;
	}//isId end
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
 public int isId(String id, String pw) {
	 Connection con=null;
	 PreparedStatement pstmt=null;
	 ResultSet rs=null;
	 int result=-1;//아이디가 존재하지 않습니다.
	 try {
		 con = ds.getConnection();
		 
		 String sql = "select id, password from member where id = ? ";
		 pstmt = con.prepareStatement(sql);
		 pstmt.setString(1, id);
		 rs = pstmt.executeQuery();
		 
		 if(rs.next()){
			 if(rs.getString(2).contentEquals(pw)){
				 result = 1; //아이디와 비밀번호 일치하는 경우
			 }else {
				 result = 0; //비밀번호가 일치하지 않는 경우
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
		}//isId end
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
