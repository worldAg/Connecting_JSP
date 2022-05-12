package bo.board.action;

import java.io.IOException;
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

public class RecentBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		List<Board> boardlist = null;
		
		boardlist = dao.getRecentBoard();
		
		if (boardlist != null) {
			JsonObject object = new JsonObject();
			JsonElement je = new Gson().toJsonTree(boardlist);
			System.out.println("JsonElement type - je.toString(): \n" + je.toString());
			object.add("boardlist", je);
			
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append(object.toString());
			System.out.println("Java에서 JSP/AJAX로 보내는 결과 JsonObject의 toString(): \n" + object.toString());
		}
		
		return null;
	}

}
