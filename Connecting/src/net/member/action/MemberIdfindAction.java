package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import net.member.db.MemberDAO;

public class MemberIdfindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		MemberDAO mdao = new MemberDAO();
		String id = mdao.searchID(name, email);

		if (id == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String message = "정보를 다시 입력해주세요";
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

		// 메일 전송
		SendmailAction sendmail = new SendmailAction();
		sendmail.sendMail(email, "요청하신 아이디는 " + id + " 입니다.");

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("login.net");
		return forward;
	}

}