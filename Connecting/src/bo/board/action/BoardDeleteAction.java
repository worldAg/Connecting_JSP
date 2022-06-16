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
		
		BoardDAO dao = new BoardDAO();
		int boardId = Integer.parseInt(request.getParameter("num"));
		int result = 0;

		response.setContentType("text/html;charset=utf-8");
		result = dao.boardDelete(boardId);

		PrintWriter out = response.getWriter();
		out.println("<script>");
		
		if (result == 1) { // 삭제 성공
			out.println("alert('해당 게시글이 삭제되었습니다.');");
			out.println("location.href='boardList.bo';");
		} else { // 삭제 실패
			out.println("alert('데이터를 삭제하지 못했습니다.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
		
		return null;
	}

}
