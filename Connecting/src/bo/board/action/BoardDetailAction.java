package bo.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.board.db.BoardBean;
import bo.board.db.BoardDAO;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int boardId = Integer.parseInt(request.getParameter("num").trim());
		BoardDAO dao = new BoardDAO();

		BoardBean boarddata = dao.getDetail(boardId);

		// boarddata == null; error 테스트를 위한 값 설정
		// dao에서 글의 내용을 읽지 못했을 경우 null을 반환
		if (boarddata == null) {
			System.out.println("게시글 상세보기 실패...");
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			// request.setAttribute("message", "글 상세 데이터를 읽지 못했습니다.");
			forward.setPath("BoardList.bo");
			return forward;
		}
		System.out.println("게시글 상세보기 성공...");

		// boarddata 객체를 request 객체에 저장한다.
		request.setAttribute("boarddata", boarddata);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		// 글 내용 보기 페이지로 이동하기 위해 경로를 설정한다.
		forward.setPath("board/boardView.jsp");
		return forward;
	}

}
