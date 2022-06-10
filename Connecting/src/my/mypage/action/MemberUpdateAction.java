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

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		MemberDAO dao = new MemberDAO();
		Member data = dao.getUserInfo(id);

		forward.setPath("mypage/member_modify.jsp");
		forward.setRedirect(false);
		request.setAttribute("memberInfo", data);
		return forward;
		
	}

}