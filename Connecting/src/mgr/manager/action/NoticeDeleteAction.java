package mgr.manager.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mgr.manager.db.NoticeDAO;


public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NoticeDAO ndao = new NoticeDAO();
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int result = ndao.noticeDelete(notice_id);
		if (result == 1) {
			out.println("<script>");
			out.println("alert('공지사항을 삭제했습니다.');");
			out.println("location.href='noticeList.mgr'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('공지사항 삭제 실패입니다.');");
			out.println("history.back()");
			out.println("</script>");
		}
		out.close();
		return null;
	}

}
