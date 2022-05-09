package bo.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import bo.board.db.Board;
import bo.board.db.BoardDAO;

public class BoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int board_id = Integer.parseInt(request.getParameter("board_id").trim());
		 
		ActionForward forward = new ActionForward();
		BoardDAO bdao = new BoardDAO();
		Board bo = new Board();

		bo = bdao.getBoardById(board_id);

		if (bo == null) {
			System.out.println("게시판 불러오기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "회원정보 불러오기에 실패하였습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}

		System.out.println("게시판 불러오기 성공");

		request.setAttribute("boardInfo", bo);

		forward.setRedirect(false);
		forward.setPath("board/board_update.jsp");

		return forward;
	}

}
