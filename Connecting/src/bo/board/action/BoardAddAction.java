package bo.board.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import bo.board.db.Board;
import bo.board.db.BoardDAO;

public class BoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		BoardDAO dao = new BoardDAO();
		Board data = new Board();

		String realFolder = "";
		String saveFolder = "resources/board_upload";
		int fileSize = 5 * 1024 * 1024; // 업로드할 파일의 최대 사이즈(5MB)

		// 실제 저장 경로 지정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder = " + realFolder);
		boolean result = false;
		
		HttpSession session = request.getSession(); 
		String user_id = (String) session.getAttribute("id");
		
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8",
					new DefaultFileRenamePolicy());
			
			data.setUser_id(user_id);
			data.setTitle(multi.getParameter("title"));
			data.setCategory(Integer.parseInt(multi.getParameter("category")));
			data.setLoc(Integer.parseInt(multi.getParameter("loc")));
			data.setHost(multi.getParameter("host"));
			data.setAddress(multi.getParameter("address"));
			data.setStart_time(multi.getParameter("start_time"));
			data.setEnd_time(multi.getParameter("end_time"));
			data.setStart_date(multi.getParameter("start_date"));
			data.setEnd_date(multi.getParameter("end_date"));
			data.setContent(multi.getParameter("content"));

			String filename = multi.getFilesystemName("board_img"); // 시스템 상에 업로드된 실제 파일명 얻어오기
			data.setBoard_img(filename);

			result = dao.boardInsert(data);

			// 글 등록에 실패할 경우
			if (result == false) {
				System.out.println("글 등록 실패");
				forward.setPath("error/error500.jsp");
				request.setAttribute("message", "글 등록 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}
			
			System.out.println("게시판 등록 완료");
			forward.setRedirect(true); 
			forward.setPath("boardList.bo"); // 글 등록이 완료되면 게시글 목록 페이지로 이동
			return forward;
			
		} catch (IOException ex) {
			forward.setPath("error/error500.jsp");
			request.setAttribute("message", "데이터 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}

	}

}
