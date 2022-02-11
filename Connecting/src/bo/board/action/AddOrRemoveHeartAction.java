package bo.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import bo.board.db.BoardDAO;

public class AddOrRemoveHeartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String boardId = request.getParameter("boardId");
		String process = request.getParameter("process");

		BoardDAO dao = new BoardDAO();
		JsonObject object = new JsonObject();

		if (process.equals("remove")) {
			// 사용자가 하트를 눌러 관심글에서 제거했다면
			// 첫 번째로 user_heart 테이블에서 제거해 줘야 한다.
			// 두 번째로 board_heart 테이블에서 1을 감소시켜줘야 한다.

			// result1과 result2가 다 "true"이면 ajax의 return 값으로 "true"를 넘겨주자.

			// 일단, user_heart 테이블에서 제거해주자.
			String result1 = dao.removeHeartFromMember(userId, boardId);

			// 그리고 board_heart 테이블에서 heart_num을 1 감소시켜야 한다.
			String result2 = dao.decreaseHeartNum(boardId);

			if (result1.equals("true") && result2.equals("true")) {
				object.addProperty("success", "true");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(object.toString());
			} else {
				object.addProperty("success", "false");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(object.toString());
			}
		} else if (process.equals("add")) {
			// 사용자가 하트 버튼을 클릭하여 관심글 리스트에서 추가했다면,
			// 첫 번째로 user_heart 테이블에서 추가해 줘야 한다.
			// 두 번째로, board_heart 테이블에서 1을 증가시켜 줘야 한다.

			// result1과 result2가 다 "true"이면 ajax의 return 값으로 "true"를 넘겨주자.

			String result1 = dao.addHeartFromMember(userId, boardId);

			// 그리고 board_heart 테이블에서 heart_num을 1 증가시켜야 한다.
			String result2 = dao.increadeHeartNum(boardId);

			if (result1.equals("true") && result2.equals("true")) {
				object.addProperty("success", "true");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(object.toString());
			} else {
				object.addProperty("success", "false");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(object.toString());
			}
		}
		return null;
	}

}
