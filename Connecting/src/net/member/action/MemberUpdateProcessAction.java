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

public class MemberUpdateProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		Member m = new Member();
		m.setId(id);
		m.setPassword(pass);
		m.setName(name);
		m.setEmail(email);
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.updateInfo(m);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		//수정이 된 경우
		if (result == 1) {
			out.println("alert('수정되었습니다.');");
			out.println("location.href='memberInfo.my';");
		} else {
			out.println("alert('회원정보 수정에 실패했습니다.');");
			out.println("history.back()");//비밀번호를 제외한 다른 데이터는 유지 되어 있습니다.
		}
		out.println("</script>");
		out.close();
		return null;
	}

}
