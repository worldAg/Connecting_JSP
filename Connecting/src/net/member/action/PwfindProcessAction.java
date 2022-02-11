package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class PwfindProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		MemberDAO mdao = new MemberDAO();
		String PW = mdao.searchPW(name,id , email);
		if (PW == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String message = "정보를 다시 입력해 주세요";
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		//메일보내기
		SendmailAction sendmail = new SendmailAction(); 
		sendmail.sendMail(email, " 요청하신 PW : " + PW  +"입니다");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("login.net");
		return forward;
	}

}