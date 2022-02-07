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

import bo.board.db.BoardBean;
import bo.board.db.BoardDAO;


public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		

		BoardDAO boarddao = new BoardDAO();
		List<BoardBean> boardlist = new ArrayList<BoardBean>();		
		
		/*
		 * 게시물 작성 순: 0;
		 * 관심 많은 순: 1;
		 * 시작 일자 임박 순: 2;
		 * 종료 일자 임박 순: 3;
		 */
		String orderby = request.getParameter("orderby");
		System.out.println("orderby: " + orderby);		
		
		// 로그인 성공 시 파라미터 page가 없습니다. 그래서 초기 값이 필요.
		int page = 1; // 보여줄 페이지
		int limit = 5; // 한 페이지에 보여줄 게시판 글 목록의 수
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}		
		System.out.println("보여줄 페이지 - page: " + page);
		
		// 추가
		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit").trim());
		}
		System.out.println("한 페이지당 보여줄 게시물의 개수 - limit: " + limit);
				
		// 총 글의 개수
		int listcount = boarddao.getListCount();
		
		// 리스트를 받아옵니다.		
		if (orderby == null || orderby.equals("") || orderby.equals("0")) {
			boardlist = boarddao.getBoardListOrderByDefault(page, limit);			
			request.setAttribute("orderby", "0");
		} else if (orderby.equals("1")) {
			boardlist = boarddao.getBoardListOrderByHeart(page, limit);			
			request.setAttribute("orderby", "1");
		} else if (orderby.equals("2")) {
			boardlist = boarddao.getBoardListOrderByStartDate(page, limit);			
			request.setAttribute("orderby", "2");
		} else if (orderby.equals("3")) {
			boardlist = boarddao.getBoardListOrderByEndDate(page, limit);			
			request.setAttribute("orderby", "3");
		}
		
		/*
		 * 총 페이지 개수: (DB에 저장된 총 리스트의 수 + 한 페이지에서 보여주는 리스트의 수 - 1) / 한 페이지에서 보여주는 리스트의 수
		 * 
		 * 예를 들어 한 페이지에서 보여주는 리스트의 수가 10 개인 경우...
		 * 		예1) DB에 저장된 총 리스트의 수가 0이면 총 페이지의 수는 0페이지
		 * 		예2) DB에 저장된 총 리스트의 수가 (01~10)이면, 총 페이지의 수는 1페이지
		 * 		예3) DB에 저장된 총 리스트의 수가 (11~20)이면, 총 페이지의 수는 2페이지
		 * 		예4) DB에 저장된 총 리스트의 수가 (21~30)이면, 총 페이지의 수는 3페이지
		 */
		int maxpage = (listcount + limit - 1) / limit; // limit은 한 페이지당 보여줄 글의 개수
		
		System.out.println("한 페이지당 보여줄 글의 개수: " + limit + ",    필요한 총 페이지 개수: " + maxpage);
		
		/*
		 * startpage: 현재 페이지 그룹에서 맨 처음에 표시될 페이지 수를 의미.
		 * ([1], [11], [21] 등...) 보여줄 페이지가 30개일 경우,
		 * [1][2][3]...[30]까지 다 표시하기에는 너무 많기 때문에 보통 한 페이지에는 10페이지 정도까지 이동할 수 있게 표시.
		 * 
		 * 예) 페이지 그룹이 아래와 같은 경우,
		 * 		[1][2][3][4][5][6][7][8][9][10]
		 * 페이지 그룹의 시작 페이지는 startpage에, 마지막 페이지는 endpage에 구합니다.
		 * 
		 * 예로, 1~10페이지의 내용을 타나낼때 페이지 그룹은 [1][2]...[10]으로 표시되고,
		 * 11~20페이지의 내용을 나타낼때는 페이지 그룹은 [11][12]...[20]까지 표시된다. 
		 */
		
		// page == 보여줄 페이지
		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이징그룹에 보여줄 시작 페이지는? - startpage: " + startpage);
		
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이징그룹에 보여줄 마지막 페이지는? - endpage: " + endpage);
		
		/*
		 * 마지막 그룹의 마지막 페이지 값은 최대 페이지 값이다.
		 * 예로, 마지막 페이지 그룹이 [21]~[30]인 경우에는
		 * 시작페이지(startpage==21), 마지막페이지(endpage==30) 이지만, 
		 * 최대 필요한 페이지(maxpage)가 25라면, [21][22]...[25]까지만 표시되도록 해야된다.
		 */
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
			 * JsonObject에 List 형식을 담을 수 있는 addProperty 메서드는 없음
			 * void com.google.gson.JsonObject.add(String property, JsonElement value) 메서드를 이용하여 저장해야 함
			 * List 형식을 JsonElement로 바꿔 줘야만 object에 저장할 수 있음
			 * List => JsonElement
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
