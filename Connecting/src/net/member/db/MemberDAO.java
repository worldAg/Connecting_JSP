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
		 			"INSERT INTO member (id, password, name, email) VALUES (?,?,?,?)");
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
	}//isId end
 public int isId(String id, String pass) {
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
			 if(rs.getString(2).contentEquals(pass)){
				 result = 1; //아이디와 비밀번호 일치하는 경우
			 }else {
				 result = 0; //비밀번호가 일치하지 않는 경우
			 }
		 }
	 } catch (Exception e) {
		 e.printStackTrace();}finally {
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
public Member member_info(String id) {
	Member m = null;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try {
		con = ds.getConnection();
		
		String sql = "select * from member where id = ? ";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()){
			m = new Member();
			m.setID(rs.getString(1));
			m.setPASSWORD(rs.getString(2));
			m.setNAME(rs.getString(3));
			m.setEMAIL(rs.getString(4));
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (rs !=null)
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
		if (con != null)
			try {
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
return m;
}
public int update(Member m) {
	Connection con=null;
	PreparedStatement pstmt=null;
	int result=0;
	try {
		 con = ds.getConnection();
		 
		 String sql = "update member set name = ?, age= ? , gender = ?, email = ?, memberfile=? "
				 + " 		where id = ?";
		 pstmt = con.prepareStatement(sql);
		 pstmt.setString(1, m.getNAME());
		 pstmt.setString(2, m.getEMAIL());
		 pstmt.setString(3, m.getID());
		 result = pstmt.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
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
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
return result;
}
public int getListCount() {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int x = 0;
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement("select count(*) from member where id != 'admin'");
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			x = rs.getInt(1);
		}
	} catch (Exception ex) {
		ex.printStackTrace();
		System.out.println("getListCount() 에러: " + ex);
	} finally {
		if (rs != null)
			 try {
				 rs.close();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
	if (pstmt != null)
		try {
			pstmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	if (con != null)
		try {
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
}
return x;
}
public List<Member> getList(String field, String value, int page, int limit) {
	List<Member> list = new ArrayList<Member>();
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try {
		con = ds.getConnection();
		
		String sql = "select * "
				+ "  from (select b.*, rownum rnum"
				+ "  		from(select * from member "
				+ "          where id != 'admin'"
				+ "         and " + field + " like ? "
				+ "     order by id) b"
				+ "       )"
				+ "        where rnum between ? and ?";
		
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%"+value+"%");
		
		int startrow = (page - 1) * limit + 1;
	
		int endrow = startrow + limit - 1;
				
		pstmt.setInt(2, startrow);
		pstmt.setInt(3, endrow);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Member m = new Member();
			m.setID(rs.getString("id"));
			m.setPASSWORD(rs.getString(2));
			m.setNAME(rs.getString(3));
			m.setEMAIL(rs.getString(4));
			list.add(m);
		}
		}catch (SQLException ex) {
			ex.printStackTrace();
	} finally {
		if (rs != null)
			try {
				rs.close();
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
	return list;
	}
public int getListCount(String field, String value) {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int x = 0;
	try {
		con = ds.getConnection();
		String sql = "select count(*) from member "
				+ "where id != 'admin'"
				+ "and " + field + " like ?";
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%"+value+"%");
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			x = rs.getInt(1);
		}
	} catch (Exception ex) {
		ex.printStackTrace();
		System.out.println("getListCount() 에러: " + ex);
	} finally {
		if (rs != null)
			 try {
				 rs.close();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
	if (pstmt != null)
		try {
			pstmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	if (con != null)
		try {
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
}
return x;
}
public List<Member> getList(int page, int limit) {
	List<Member> list = new ArrayList<Member>();
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try {
		con = ds.getConnection();
		
		String sql = "select * "
				+ "  from (select b.*, rownum rnum"
				+ "  		from(select * from member "
				+ "          where id != 'admin'"
				+ "     order by id) b"
				+ "       )"
				+ "        where rnum between ? and ?";
		
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		
		
		int startrow = (page - 1) * limit + 1;
		 
		int endrow = startrow + limit - 1;
				
		pstmt.setInt(1, startrow);
		pstmt.setInt(2, endrow);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Member m = new Member();
			m.setID(rs.getString("id"));
			m.setPASSWORD(rs.getString(2));
			m.setNAME(rs.getString(3));
			m.setEMAIL(rs.getString(4));
			list.add(m);
		}
		}catch (SQLException ex) {
			ex.printStackTrace();
	} finally {
		if (rs != null)
			try {
				rs.close();
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
	return list;
	}
public int delete(String id) {
	Connection con =null;
	PreparedStatement pstmt=null;
	int result=0;
	try {
		 con = ds.getConnection();
		 String sql = "delete from member where id = ? ";
		 pstmt = con.prepareStatement(sql);
		 pstmt.setString(1, id);
		 result=pstmt.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
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
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
	return result;
	}
}

