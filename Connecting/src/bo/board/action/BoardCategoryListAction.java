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

public class BoardCategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String category = request.getParameter("category");
		String orderby = request.getParameter("orderby");

		BoardDAO boarddao = new BoardDAO();
		List<Board> boardlist = new ArrayList<Board>();

		int page = 1; // 보여줄 페이지
		int limit = 5; // 한 페이지에 보여줄 게시판 글 목록의 수

		if (request.getParameter("page") != null && !request.getParameter("page").equals("")) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}

		if (orderby == null || orderby.equals("")) {
			orderby = "0";
		}

		System.out.println("category: " + category + "\norderby: " + orderby);

		System.out.println("보여줄 페이지 - page: " + page);

		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit").trim());
		}

		System.out.println("한 페이지당 보여줄 게시물의 개수 - limit: " + limit);

		// 총 글의 개수
		int listcount = boarddao.getListCountByCategory(category);

		// 리스트 가져오기
		boardlist = boarddao.getBoardListByCategoryOrderBy(page, limit, category, orderby);

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
			request.setAttribute("orderby", orderby);

			ActionForward forward = new ActionForward();
			forward.setRedirect(false);

			// 글 목록 페이지로 이동하기 위해 경로 지정
			forward.setPath("board/boardList.jsp");
			return forward;
		} else {
			System.out.println("state == ajax");

			// 위에서 request로 담았던 것을 JsonObject에 담습니다.
			JsonObject object = new JsonObject();
			object.addProperty("page", page);
			object.addProperty("maxpage", maxpage); // 필요한 총 페이지 개수
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount", listcount); // 총 글의 개수
			object.addProperty("limit", limit);

			/*
			 * JsonObject에 List 형식을 담을 수 있는 addProperty 메서드는 없음 void
			 * com.google.gson.JsonObject.add(String property, JsonElement value) 메서드를 이용하여
			 * 저장해야 함 List 형식을 JsonElement로 바꿔 줘야만 object에 저장할 수 있음 List => JsonElement
			 */

			JsonElement je = new Gson().toJsonTree(boardlist);
			System.out.println("JsonElement type - je.toString(): \n" + je.toString());
			object.add("boardlist", je);

			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append(object.toString());
			System.out.println("Java에서 JSP/AJAX로 보내는 결과 JsonObject의 toString(): \n" + object.toString());
			return null;
		}

	}

}
