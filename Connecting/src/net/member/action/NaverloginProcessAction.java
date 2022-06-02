package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import net.member.db.MemberDAO;

public class NaverloginProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		HttpSession session = request.getSession();

		session.setAttribute("email", email);
		session.setAttribute("id", name);
		forward.setRedirect(true);
		forward.setPath("main.jsp");
		return forward;

	}

}
