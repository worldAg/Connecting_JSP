package bo.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import bo.board.db.BoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		BoardDAO bdao = new BoardDAO();
		int board_id = Integer.parseInt(request.getParameter("board_id"));

		int result = 0;

		response.setContentType("text/html;charset=utf-8");
		result = bdao.deleteBoard(board_id);

		PrintWriter out = response.getWriter();
		out.println("<script>");
		// 삭제 된 경우
		if (result == 1) {
			out.println("alert('게시글이 삭제 되었습니다.');");
			out.println("location.href='BoardList.bo';");
		} else {
			out.println("alert('게시글 삭제에 실패 하였습니다.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();

		return null;
	}

}
