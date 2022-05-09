package bo.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import action.Action;
import action.ActionForward;
import bo.board.db.BoardDAO;

public class IsAddedToMemberTableAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String boardId = request.getParameter("boardId");

		BoardDAO dao = new BoardDAO();
		String added = dao.isAddedToMemberTable(userId, boardId);

		System.out.println("IsAddedToMemberTableAction...");

		JsonObject object = new JsonObject();
		object.addProperty("isAdded", added);

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append(object.toString());

		System.out.println("IsAddedToMemberTableAction에서 조회한 결과: " + object.toString());
		return null;
	}

}
