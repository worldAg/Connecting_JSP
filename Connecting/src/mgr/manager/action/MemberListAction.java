package mgr.manager.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mgr.manager.db.*;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		
		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		
		List<Member> list = null;
		int listcount = 0;		
		String search_word = "";
		
		if (request.getParameter("search_word") == null || request.getParameter("search_word").equals("")) {
			// 총 리스트 수를 받아오기
			listcount = mdao.memberCount();
			list = mdao.memberList(page, limit);
		} else {
			search_word = request.getParameter("search_word");
			listcount = mdao.memberCount(search_word);
			list = mdao.memberList(search_word, page, limit);
		}
		
		int maxpage = (listcount + limit -1) / limit;
		System.out.println("총 페이지 수 = " + maxpage);
		
		int startpage = ((page - 1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " +startpage);
		
		if (endpage > maxpage) endpage = maxpage;
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		
		// 현재 페이지에 표시할 첫 페이지 수 
		request.setAttribute("startpage", startpage);
		// 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);
		// 총 글의 수
		request.setAttribute("listcount", listcount);
		request.setAttribute("totallist", list);
		request.setAttribute("search_word", search_word);
		
		forward.setPath("mgrMain.mgr?page=mgr_member");
		forward.setRedirect(false);
		return forward;
	}


}
