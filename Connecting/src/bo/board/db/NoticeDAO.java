package bo.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		} catch (Exception e) {
			System.out.println("Oracle DB 연결 실패: " + e.getMessage());
		}
	}
	
	public List<NoticeBean> getAllNotices() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeBean> list = null;
		boolean firstLoop = true;
		
		try {
			String selectSQL = "SELECT * " +
		                       "FROM NOTICE_COPY " +
					           "ORDER BY NOTICE_ID DESC";
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if (firstLoop) {
					list = new ArrayList<>();
					firstLoop = false;
				}
				
				String notice_id = rs.getString("NOTICE_ID");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String write_date = rs.getString("WRITE_DATE");
				
				NoticeBean notice = new NoticeBean();
				notice.setNotice_id(notice_id);
				notice.setTitle(title);
				notice.setContent(content);
				notice.setWrite_date(write_date);
				
				list.add(notice);
			}
		} catch (Exception e) {
			System.out.println("NoticeDAO - getAllNotices() exception - " + e.getMessage());
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
		return list;
	}
}
