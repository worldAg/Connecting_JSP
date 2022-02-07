package my.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import my.mypage.db.MemberDAO;


public class MemberImageUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String realFolder = "";
		
		//WebContent아래에 꼭 폴더 생성
		String saveFolder = "memberupload";
		
		int fileSize = 5 * 1024 * 1024; // 업로드할 파일의 최대 사이즈 입니다. 5MB
		
		//실제 저장 경로를 지정합니다.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println(realFolder);
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8",
					new DefaultFileRenamePolicy());
			
			String id = multi.getParameter("id");
			String profile = multi.getFilesystemName("profile");
			
			System.out.println("파일=" + profile);
			
			
			MemberDAO mdao = new MemberDAO();
			int result = mdao.updateImg(profile,id);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			//수정이 된 경우
			if (result == 1) {
				out.println("alert('수정되었습니다.');");
				out.println("location.href='memberInfo.my';");
			} else {
				out.println("alert('회원 정보 수정에 실패했습니다.');");
				out.println("history.back()");
			}
			out.println("</script>");
			out.close();
			return null;
		}catch(IOException ex) {
			ActionForward forward = new ActionForward();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "프로필 사진 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
	}

}
