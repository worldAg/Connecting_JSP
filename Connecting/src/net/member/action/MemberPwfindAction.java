package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import net.member.db.MemberDAO;

public class MemberPwfindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		MemberDAO mdao = new MemberDAO();
		String pw = mdao.searchPW(id, name, email);

		if (pw == null) {
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
		sendmail.sendMail(email, "요청하신 비밀번호는 " + pw + " 입니다.");

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("login.net");
		return forward;
	}

}