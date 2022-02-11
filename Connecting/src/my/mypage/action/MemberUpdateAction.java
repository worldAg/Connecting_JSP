package my.mypage.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import my.mypage.db.Member;
import my.mypage.db.MemberDAO;



public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//HttpSession session = request.getSession();


		//String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		Member m = new Member();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id"); 
		
		m = mdao.getUser(id);
		

		if(m == null) {
			System.out.println("회원정보 불러오기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "회원정보 불러오기에 실패하였습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		
		System.out.println("회원정보 불러오기 성공");
		
		
		request.setAttribute("memberInfo", m);
		forward.setRedirect(false);

		forward.setPath("mypage/updateForm.jsp");
		
		return forward;
	}

}
