package bo.board.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import action.Action;
import action.ActionForward;
import bo.board.db.Board;
import bo.board.db.BoardDAO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		

		BoardDAO dao = new BoardDAO();
		List<Board> boardlist = new ArrayList<Board>();
		
		String orderby = request.getParameter("orderby");
		String category = request.getParameter("category");
		String loc = request.getParameter("loc");
		
		if (orderby == null || orderby.equals("")) orderby = "0";
		if (category == null || category.equals("")) category = "all";
		if (loc == null || loc.equals("")) loc = "all";
		
		System.out.println("orderby: " + orderby + ", category: " + category + ", loc: " + loc);
		
		int page = 1;   // 보여줄 페이지
		int limit = 10; // 한 페이지에 보여줄 글의 개수
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}
		System.out.println("page: " + page);

		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit").trim());
		}
		System.out.println("limit: " + limit);
				
		int listcount = dao.getListCount(category, loc); // 총 게시글 개수
		boardlist = dao.getBoardList(page, limit, orderby, category, loc);	
		
		int maxpage = (listcount + limit - 1) / limit; // 총 페이지 수
		System.out.println("총 페이지 수: " + maxpage);
		
		int startpage = ((page - 1) / 10) * 10 + 1; // 현재 페이지 그룹의 시작 페이지 ex. 10페이지씩 그룹이면 [1], [11], [21], ...
		System.out.println("startpage: " + startpage);
		
		int endpage = startpage + 10 - 1;			// 현재 페이지 그룹의 마지막 페이지  ex. 10페이지씩 그룹이면 [10], [20], [30], ...
		System.out.println("endpage: " + endpage);
		
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		
		String state = request.getParameter("state");
		System.out.println("state: " + state);		
		
		if (state == null) {
			System.out.println("state == null");
			request.setAttribute("page", page);		  		// 현재 보여줄 페이지
			request.setAttribute("maxpage", maxpage); 		// 필요한 총 페이지 수
			request.setAttribute("startpage", startpage); 	// 현재 페이지에 표시할 첫 페이지 수
			request.setAttribute("endpage", endpage); 		// 현재 페이지에 표시할 끌 페이지 수
			request.setAttribute("listcount", listcount);	// 총 글의 개수
			request.setAttribute("boardlist", boardlist); 	// 해당 페이지의 글 목록
			request.setAttribute("limit", limit); 			// 한 페이지당 보여줄 글의 개수
			request.setAttribute("orderby", orderby);
			request.setAttribute("category", category);
			request.setAttribute("loc", loc);

			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/board/board_list.jsp");
			return forward;			
		} else {
			System.err.println("state == ajax");
			
			// 위에서 request로 담았던 것을 JsonObject에 담기
			JsonObject object = new JsonObject();
			object.addProperty("page", page);
			object.addProperty("maxpage", maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount", listcount);
			object.addProperty("limit", limit);
			object.addProperty("orderby", orderby);
			object.addProperty("category", category);
			object.addProperty("loc", loc);
	
			// object에 저장할 수 있도록 List형식을 JsonElement로 변경
			JsonElement je = new Gson().toJsonTree(boardlist);
			System.out.println("boardlist(JsonElement type): " + je.toString());
			object.add("boardlist", je);
			
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append(object.toString());
			System.out.println("ajax 결과: " + object.toString());
			return null;
		}
		
	}

}
