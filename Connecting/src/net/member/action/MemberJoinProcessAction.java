package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberJoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("pass");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		Member data = new Member();
		data.setId(id);			data.setPassword(password);
		data.setName(name);		data.setEmail(email);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberInsert(data);
		if(result==0) {
			System.out.println("회원가입 실패입니다.");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "회원가입 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		out.println("<script>");
		if (result == 1) { 
			out.println("alert('회원가입을 축하합니다.');");
			out.println("location.href='login.net';");
		} else if (result == -1) {
			out.println("alert('중복된 아이디입니다. 다시 입력해주세요.');");
			out.println("history.back()"); // 비밀번호를 제외한 다른 데이터 유지되면서 back.
		}
		out.println("</script>");
		out.close();
		return null;
	}

}
