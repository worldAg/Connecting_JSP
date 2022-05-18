package bo.board.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import bo.board.db.Board;
import bo.board.db.BoardDAO;

public class BoardSearchBarListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		List<Board> boardlist = new ArrayList<Board>();
		String keyword = request.getParameter("keyword").trim();

		int page = 1;   // 보여줄 페이지
		int limit = 10; // 한 페이지에 보여줄 글의 개수

		if (request.getParameter("page") != null && !request.getParameter("page").equals("")) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}

		int listcount = dao.getListCountBySearchBar(keyword); // 총 글의 개수
		boardlist = dao.getBoardListBySearchBar(page, limit, keyword);

		int maxpage = (listcount + limit - 1) / limit; // 총 페이지 수
		System.out.println("총 페이지 수: " + maxpage);
		
		int startpage = ((page - 1) / 10) * 10 + 1; // 현재 페이지 그룹의 시작 페이지 ex. 10페이지씩 그룹이면 [1], [11], [21], ...
		System.out.println("startpage: " + startpage);
		
		int endpage = startpage + 10 - 1;			// 현재 페이지 그룹의 마지막 페이지  ex. 10페이지씩 그룹이면 [10], [20], [30], ...
		System.out.println("endpage: " + endpage);
		
		if (endpage > maxpage) {
			endpage = maxpage;
		}

		request.setAttribute("page", page);		  		// 현재 보여줄 페이지
		request.setAttribute("maxpage", maxpage); 		// 필요한 총 페이지 수
		request.setAttribute("startpage", startpage); 	// 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("endpage", endpage); 		// 현재 페이지에 표시할 끌 페이지 수
		request.setAttribute("listcount", listcount);	// 총 글의 개수
		request.setAttribute("boardlist", boardlist); 	// 해당 페이지의 글 목록
		request.setAttribute("limit", limit); 			// 한 페이지당 보여줄 글의 개수
		request.setAttribute("keyword", keyword);
			
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/board/board_list.jsp");
		return forward;
		
	}

}
