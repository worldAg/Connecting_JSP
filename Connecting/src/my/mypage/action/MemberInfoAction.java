package my.mypage.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import my.mypage.db.Board;
import my.mypage.db.BoardDAO;
import my.mypage.db.Member;
import my.mypage.db.MemberDAO;


public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		BoardDAO bdao = new BoardDAO();
		Member m = new Member();
		List<Board> boardlist = new ArrayList<Board>();
		
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
		
		m = mdao.getUser(id);
		boardlist = bdao.getMyBoard(id);
		
		request.setAttribute("memberInfo", m);
		request.setAttribute("myboard", boardlist);
		
		forward.setRedirect(false);//주소 변경없이 jsp페이지의 내용을 보여줍니다.
		forward.setPath("mypage.jsp");
		return forward;
	}

}
