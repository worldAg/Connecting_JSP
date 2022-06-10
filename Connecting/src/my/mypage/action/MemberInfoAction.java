package my.mypage.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberDAO memberDao = new MemberDAO();
		Member member = new Member();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		member = memberDao.getUserInfo(id);
		request.setAttribute("memberInfo", member);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("mypage/mypage.jsp");
		return forward;
	}

}