package mgr.manager.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import mgr.manager.db.NoticeBean;
import mgr.manager.db.NoticeDAO;


public class NoticeModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		NoticeDAO ndao = new NoticeDAO();
		NoticeBean n = new NoticeBean();
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		n = ndao.noticeDetail(notice_id);
		
		// 글 내용 불러오기를 실패할 경우
		if (n == null) {
			System.out.println("(수정)상세보기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 (수정)상세보기 실패입니다.");
			forward.setPath("main.jsp");
			return forward;
		}
		System.out.println("(수정)상세보기 성공");

		request.setAttribute("noticedata", n);
		forward.setRedirect(false);
		
		forward.setPath("manger/mgr_noticeModify.jsp");
		return forward;
	}

}
