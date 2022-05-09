package mgr.manager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import mgr.manager.db.NoticeBean;
import mgr.manager.db.NoticeDAO;

public class NoticeAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeBean noticedata = new NoticeBean();
		noticedata.setTitle(title);
		noticedata.setContent(content);
		
		response.setContentType("text/html;charset=utf-8");
		
		NoticeDAO noticedao = new NoticeDAO();
		boolean result = noticedao.noticeInsert(noticedata);
		
		if(result == false) {
			System.out.println("공지사항 등록 실패");
			forward.setRedirect(false);
			request.setAttribute("messsage", "공지사항 등록 실패입니다.");
			forward.setPath("noticeWrite.mgr");
			return forward;
		}
		System.out.println("공지사항 등록 완료");
		forward.setRedirect(true);
		forward.setPath("noticeList.mgr");
		return forward;
		
	}

}
