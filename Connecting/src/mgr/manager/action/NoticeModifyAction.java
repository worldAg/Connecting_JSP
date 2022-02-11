package mgr.manager.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mgr.manager.db.NoticeBean;
import mgr.manager.db.NoticeDAO;

public class NoticeModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		NoticeDAO ndao = new NoticeDAO();
		NoticeBean noticedata = new NoticeBean();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		
		noticedata.setTitle(title);
		noticedata.setContent(content);
		noticedata.setNotice_id(notice_id);
		
		boolean result = ndao.noticeModify(noticedata);
		
		if(result == false) {
			System.out.println("공지사항 수정 실패");
			forward.setRedirect(false);
			request.setAttribute("messsage", "공지사항 수정 실패입니다.");
			forward.setPath("noticeList.mgr");
			return forward;
		}
		System.out.println("공지사항 등록 완료");
		forward.setRedirect(true);
		forward.setPath("noticeList.mgr");
		return forward;
		
	}
		
}
