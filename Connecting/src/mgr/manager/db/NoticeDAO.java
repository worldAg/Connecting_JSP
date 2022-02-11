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

public class NoticeDAO {
	private DataSource ds;

	public NoticeDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	
	public boolean noticeInsert(NoticeBean notice) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();

			String max_sql = "(SELECT NVL(MAX(NOTICE_ID),0)+1 FROM NOTICE)";

			String sql = "INSERT INTO NOTICE " + "(NOTICE_ID, TITLE, CONTENT) "
					   + "VALUES(" + max_sql + ", ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
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
	
	public List<NoticeBean> getNoticeList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeBean> noticeList = new ArrayList<NoticeBean>();
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement("SELECT * FROM NOTICE ORDER BY WRITE_DATE DESC");;
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeBean notice = new NoticeBean();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setWrite_date(rs.getString("write_date"));
				noticeList.add(notice);
				
			}
		} catch (Exception e) {
			System.out.println("getNoticeList() 에러 : " + e);
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
		return noticeList;
		
	}
	
	public int noticeDelete(int notice_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "DELETE FROM NOTICE WHERE NOTICE_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
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
	
	public NoticeBean noticeDetail(int notice_id) {
		NoticeBean n = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			
			String sql = "SELECT * FROM NOTICE WHERE NOTICE_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				n = new NoticeBean();
				n.setNotice_id(rs.getInt("notice_id"));
				n.setTitle(rs.getString("title"));
				n.setContent(rs.getString("content"));
				n.setWrite_date(rs.getString("write_date"));
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
		return n;
	}
	
	public boolean noticeModify(NoticeBean noticedata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICE " 
				   + "SET TITLE = ?, CONTENT = ? "
				   + "WHERE NOTICE_ID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noticedata.getTitle());
			pstmt.setString(2, noticedata.getContent());
			pstmt.setInt(3, noticedata.getNotice_id());
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("성공 업데이트");
				return true;
			}
		} catch (Exception e) {
			System.out.println("noticeModify() 에러 : " + e);
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
	
	
	
	
}
