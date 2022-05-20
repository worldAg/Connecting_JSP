package bo.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import action.Action;
import action.ActionForward;
import bo.board.db.HeartDAO;

public class HeartForBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String boardId = request.getParameter("boardId");

		HeartDAO dao = new HeartDAO();
		String isAdded = dao.getMemberHeartForBoard(userId, boardId);

		JsonObject object = new JsonObject();
		object.addProperty("isAdded", isAdded);

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append(object.toString());

		System.out.println("HeartForBoardAction 조회 결과: " + object.toString());
		return null;
	}

}
