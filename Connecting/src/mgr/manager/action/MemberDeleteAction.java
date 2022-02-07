package mgr.manager.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mgr.manager.db.*;


public class MemberDeleteAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO mdao = new MemberDAO();
		String id = request.getParameter("id");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int result = mdao.memberDelete(id);
		if (result == 1) {
			out.println("<script>");
			out.println("alert('회원 정보를 삭제했습니다.');");
			out.println("location.href='memberList.mgr'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원 탈퇴 실패입니다.');");
			out.println("history.back()");
			out.println("</script>");
		}
		out.close();
		return null;
	}

}
