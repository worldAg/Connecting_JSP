package mgr.manager.db;

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
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	
	public int memberCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM MEMBER");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("memberCount() 에러 : " + e);
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
		return x;
	}
	
	public int memberCount(String value) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			String sql = "SELECT COUNT(*) FROM MEMBER "
					   + "WHERE ID != 'admin' "
					   + "AND ID like ? ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + value + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getListCount(1) 에러 : " + e);
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
		return x;
	}
	
	// 모든 회원 조회
	public List<Member> memberList(int page, int limit) {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * "
					   + "FROM (SELECT m.*, ROWNUM RNUM"
					   + "      FROM(SELECT * FROM MEMBER "
					   + "           WHERE ID != 'admin'"
					   + "           ORDER BY ID) m"
					   + "     )"
					   + "WHERE RNUM >= ? AND RNUM <= ?";
			pstmt = conn.prepareStatement(sql);
			
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member m = new Member();
				m.setId(rs.getString(1));
				m.setPassword(rs.getString(2));
				m.setEmail(rs.getString(3));
				m.setName(rs.getString(4));
				m.setProfile(rs.getString(5));
				m.setReg_date(rs.getString(6));
				list.add(m);
			}
		} catch (Exception e) {
			System.out.println("memberList(2) 에러 : " + e);
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
		return list;
	}
	
	// 회원 ID 검색 조건에 맞는 목록
	public List<Member> memberList(String value, int page, int limit) {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * "
					   + "FROM (SELECT m.*, ROWNUM RNUM "
					   + "      FROM(SELECT * FROM MEMBER "
					   + "           WHERE ID != 'admin' "
					   + "           AND ID LIKE ? "
					   + "           ORDER BY ID) m"
					   + "     ) "
					   + "WHERE RNUM BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
	
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member m = new Member();
				m.setId(rs.getString(1));
				m.setPassword(rs.getString(2));
				m.setEmail(rs.getString(3));
				m.setName(rs.getString(4));
				m.setProfile(rs.getString(5));
				m.setReg_date(rs.getString(6));
				list.add(m);
			}
		} catch (Exception e) {
			System.out.println("memberList(3) 에러 : " + e);
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
		return list;
	}
	
	public Member memberInfo(String id) {
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			
			String sql = "SELECT * FROM MEMBER WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m = new Member();
				m.setId(rs.getString(1));
				m.setPassword(rs.getString(2));
				m.setEmail(rs.getString(3));
				m.setName(rs.getString(4));
				m.setProfile(rs.getString(5));
				m.setReg_date(rs.getString(6));
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
		return m;
	}
	
	public int memberDelete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "DELETE FROM MEMBER WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}

	
}
