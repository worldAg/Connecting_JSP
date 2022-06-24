package bo.board.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import bo.board.db.Board;
import bo.board.db.BoardDAO;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		BoardDAO dao = new BoardDAO();
		Board data = new Board();
		int result = 0;

		String realFolder = "";
		String saveFolder = "resources/board_upload";
		int fileSize = 5 * 1024 * 1024; // 업로드할 파일의 최대 사이즈(5MB)

		// 실제 저장 경로 지정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println(realFolder);
		
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8",
					new DefaultFileRenamePolicy());
			data.setBoard_id(Integer.parseInt(multi.getParameter("board_id")));
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
			
			String check = multi.getParameter("check");
			if (check != null) { // 기존 파일 그대로 사용하는 경우
				data.setBoard_img(check);
			} else {
				// 업로드된 파일의 시스템 상에 업로드된 실제 파일명 얻어오기
				data.setBoard_img(multi.getFilesystemName("board_img"));
			}

			result = dao.boardUpdate(data);

			// 글 수정에 실패할 경우
			if (result == 0) {
				System.out.println("게시판 수정 실패");
				forward.setRedirect(false);
				request.setAttribute("message", "게시판 수정 실패입니다.");
				forward.setPath("error/error500.jsp");
				return forward;
			}
			
			System.out.println("게시판 수정 완료");
			forward.setRedirect(true);
			forward.setPath("boardList.bo"); // 글 수정이 완료되면 게시글 목록 페이지로 이동
			return forward;

		} catch (IOException e) {
			forward.setPath("error/error500.jsp");
			request.setAttribute("message", "게시판 수정 업로드 중 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
		
	}

}
