package net.member.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import bo.board.db.Board;
import bo.board.db.BoardDAO;
import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		MemberDAO memberDao = new MemberDAO();
		Member member = new Member();
		BoardDAO boardDao = new BoardDAO();
		List<Board> boardlist = new ArrayList<Board>();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		member = memberDao.getUserInfo(id);
		boardlist = boardDao.getMyBoard(id);
		
		request.setAttribute("memberInfo", member);
		request.setAttribute("myboard", boardlist);
		
		forward.setRedirect(false);
		forward.setPath("mypage/mypage.jsp");
		return forward;
	}

}
