package my.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import net.member.db.Member;
import net.member.db.MemberDAO;


public class MemberUpdateProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberDAO dao = new MemberDAO();
		Member data = new Member();

		String realFolder = "";
		String saveFolder = "resources/profile_upload";
		int fileSize = 5 * 1024 * 1024; // 업로드할 파일의 최대 사이즈(5MB)

		// 실제 저장 경로를 지정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println(realFolder);

		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8",
					new DefaultFileRenamePolicy());
			
			String id = multi.getParameter("id");
			String password = multi.getParameter("pass");
			String name = multi.getParameter("name");
			String email = multi.getParameter("email");
			String profile_img = multi.getFilesystemName("profile_img");
			data.setId(id);
			data.setPassword(password);
			data.setName(name);
			data.setEmail(email);
			data.setProfile_img(profile_img);

			int result = dao.memberUpdateInfo(data);

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if (result == 1) { // 수정된 경우
				out.println("alert('회원정보가 수정되었습니다.');");
				out.println("location.href='mypage.net';");
			} else {
				out.println("alert('회원정보 수정 실패입니다.');");
				out.println("history.back()"); // 비밀번호를 제외한 다른 데이터는 유지되도록 함.
			}
			out.println("</script>");
			out.close();
			return null;
		} catch (IOException ex) {
			ActionForward forward = new ActionForward();
			forward.setPath("error/error500.jsp");
			request.setAttribute("message", "프로필 사진 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
		
	}

}
