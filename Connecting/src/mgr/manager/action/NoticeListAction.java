package mgr.manager.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mgr.manager.db.NoticeBean;
import mgr.manager.db.NoticeDAO;

public class NoticeListAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		NoticeDAO noticedao = new NoticeDAO();
		List<NoticeBean> noticeList = new ArrayList<NoticeBean>();
		noticeList = noticedao.getNoticeList();
		
		String state = request.getParameter("state");
		if(state == null) {
			System.out.println("state == null");
			request.setAttribute("noticeList", noticeList);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("mgrMain.mgr?page=mgr_notice");
			return forward;
		} else {
			return null;
		}
	}

}
