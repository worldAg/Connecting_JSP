package my.mypage.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.mypage.db.Board;
import my.mypage.db.BoardDAO;

public class MyHeartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO bdao = new BoardDAO();
		List<Board> boardlist = null;
		//String id = (String)session.getAttribute("id");
		String id = "hh";
		
		int page =1; //보여줄 page
		int limit = 5; //한 페이지에 보여줄 게시판 목록의 수
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 =" +page);
		
		int listcount = bdao.getMyListCount(id);
		System.out.println("총페이지 수=" + listcount);

		boardlist = bdao.getHeartBoardList(id,page,limit);
		System.out.println(boardlist);
		//총 페이지 수
		int maxpage = (listcount + limit - 1) /limit;
		
		//현재 페이지에 보여줄 시작 페이지 수
		int startpage = ((page - 1) / 10) * 10 + 1;
		//현재 페이지에 보여줄 마지막 페이지 수
		int endpage = startpage + 10 -1;
		
		//마지막 페이지가 maxpage를 초과하지 않게 설정
		if (endpage > maxpage)
			endpage = maxpage;
		
		
		
		request.setAttribute("page", page);// 현재 페이지수
		request.setAttribute("maxpage", maxpage); //최대 페이지 수
		request.setAttribute("startpage", startpage);// 현재 페이지에 표시할 첫 페이지수
		request.setAttribute("endpage", endpage);// 현재 페이지에 표시할 끝 페이지수
		request.setAttribute("listcount", listcount); //총 글의 수
		request.setAttribute("limit", limit);
		
		request.setAttribute("heartboards", boardlist);		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		forward.setPath("mypage/mylist.jsp");
		
		return forward;
	}

}
