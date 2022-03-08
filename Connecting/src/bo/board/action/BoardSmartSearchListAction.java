package bo.board.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.board.db.BoardBean;
import bo.board.db.BoardDAO;

public class BoardSmartSearchListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String category = request.getParameter("category");
		String loc = request.getParameter("loc");

		BoardDAO boarddao = new BoardDAO();
		List<BoardBean> boardlist = new ArrayList<BoardBean>();

		int page = 1; // 보여줄 페이지
		int limit = 5; // 한 페이지에 보여줄 게시판 글 목록의 수

		if (request.getParameter("page") != null && !request.getParameter("page").equals("")) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}

		if (request.getParameter("limit") != null && !request.getParameter("limit").equals("")) {
			limit = Integer.parseInt(request.getParameter("limit").trim());
		}

		int listcount = boarddao.getListCountBySmartSearch(category, loc);

		boardlist = boarddao.getBoardListBySmartSearch(page, limit, category, loc);

		int maxpage = (listcount + limit - 1) / limit; // limit은 한 페이지당 보여줄 글의 개수

		System.out.println("한 페이지당 보여줄 글의 개수: " + limit + ",    필요한 총 페이지 개수: " + maxpage);

		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이징그룹에 보여줄 시작 페이지는? - startpage: " + startpage);

		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이징그룹에 보여줄 마지막 페이지는? - endpage: " + endpage);

		if (endpage > maxpage) {
			endpage = maxpage;
		}

		String state = request.getParameter("state");
		System.out.println("state: " + state);

		if (state == null) {
			System.out.println("state == null");
			request.setAttribute("page", page); // 현재 보여줄 페이지
			request.setAttribute("maxpage", maxpage); // 필요한 총 페이지의 개수

			// 현재 페이지에 표시할 첫 페이지 수
			request.setAttribute("startpage", startpage);

			// 현재 페이지에 표시할 끌 페이지 수
			request.setAttribute("endpage", endpage);

			// 총 글의 개수
			request.setAttribute("listcount", listcount);

			// 해당 페이지의 글 목록을 갖고 있는 리스트
			request.setAttribute("boardlist", boardlist);

			// 한 페이지당 보여줄 최대 개시글 개수
			request.setAttribute("limit", limit);

			// 어떤 카테고리인지 알려주기
			request.setAttribute("category", category);

			// 정렬 방식
			request.setAttribute("loc", loc);

			ActionForward forward = new ActionForward();
			forward.setRedirect(false);

			// 글 목록 페이지로 이동하기 위해 경로 지정
			forward.setPath("board/boardSmartSearchList.jsp");
			return forward;
		} else {
			return null;
		}
	}

}
