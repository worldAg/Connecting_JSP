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

		ActionForward forward = new ActionForward();
		BoardDAO dao = new BoardDAO();
		Board data = new Board();

		int boardId = Integer.parseInt(request.getParameter("num").trim()); // 파라미터로 전달 받은 수정할 글의 번호를 boardId 변수에 저장
		
		data = dao.getBoardDetail(boardId);

		if (data == null) {
			System.out.println("(수정)상세보기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정 상세보기 실패입니다.");
			forward.setPath("error/error500.jsp");
			return forward;
		}

		System.out.println("(수정)상세보기 성공");
		request.setAttribute("boardInfo", data);
		forward.setRedirect(false);
		forward.setPath("board/board_modify.jsp");
		return forward;
	}

}
