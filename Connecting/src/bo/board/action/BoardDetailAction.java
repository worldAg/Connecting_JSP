package bo.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import bo.board.db.Board;
import bo.board.db.BoardDAO;
import net.member.db.Member;
import net.member.db.MemberDAO;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int boardId = Integer.parseInt(request.getParameter("num").trim());
		
		BoardDAO boardDAO = new BoardDAO();
		Board boardData = boardDAO.getBoardDetail(boardId);
		
		// DAO에서 글의 내용을 읽지 못했을 경우 null을 반환
		if (boardData == null) {
			System.out.println("글 상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			request.setAttribute("message", "글 상세 데이터를 읽지 못했습니다.");
			forward.setPath("error/errorNullPointer.jsp");
			return forward;
		}
		
		System.out.println("글 상세보기 성공");
		request.setAttribute("boardData", boardData); // boardData 객체를 request 객체에 저장
		
		MemberDAO memberDAO = new MemberDAO();
		Member memberData = memberDAO.getUserInfo(boardData.getUser_id());
		request.setAttribute("memberData", memberData);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board/board_detail.jsp");
		return forward;
		
	}

}
