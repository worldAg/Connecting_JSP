package mgr.manager.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import mgr.manager.db.NoticeBean;
import mgr.manager.db.NoticeDAO;

public class NoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		NoticeDAO ndao = new NoticeDAO();
		NoticeBean n = ndao.noticeDetail(notice_id);
		String id = (String) request.getSession().getAttribute("id");
		
		if (n == null) {
			forward.setPath("noticeList.mgr");
			forward.setRedirect(false);
			request.setAttribute("message", "해당 공지사항 정보가 없습니다.");
			return forward;
		}
		
		forward.setPath("manger/mgr_noticeDetail.jsp");
		forward.setRedirect(false);
		request.setAttribute("noticedata", n);
		request.setAttribute("id", id);
		return forward;
	}

}
