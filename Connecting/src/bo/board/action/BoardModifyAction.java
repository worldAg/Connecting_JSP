package bo.board.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bo.board.db.Board;
import bo.board.db.BoardDAO;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO bdao = new BoardDAO();
		Board board = new Board();
		ActionForward forward = new ActionForward();
		int result = 0;

		String realFolder = "";

		// WebContent아래에 꼭 폴더 생성
		String saveFolder = "boardupload";

		int fileSize = 5 * 1024 * 1024; // 업로드할 파일의 최대 사이즈 입니다. 5MB

		// 실제 저장 경로를 지정합니다.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println(realFolder);
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8",
					new DefaultFileRenamePolicy());
			board.setBoard_id(Integer.parseInt(multi.getParameter("board_id")));
			board.setCategory(Integer.parseInt(multi.getParameter("category")));
			board.setLoc(Integer.parseInt(multi.getParameter("loc")));
			board.setTitle(multi.getParameter("title"));
			board.setHost_name(multi.getParameter("host_name"));
			board.setAddress(multi.getParameter("address"));
			board.setStart_date(multi.getParameter("start_date"));
			board.setEnd_date(multi.getParameter("end_date"));
			board.setStart_time(multi.getParameter("start_time"));
			board.setEnd_time(multi.getParameter("end_time"));
			board.setContent(multi.getParameter("content"));

			String check = multi.getParameter("check");

			if (check != null) {
				board.setBoard_img(check);
			} else {
				board.setBoard_img(multi.getFilesystemName("board_img"));
			}

			result = bdao.boardUpdate(board);

			// 글 수정에 실패할 경우
			if (result == 0) {
				System.out.println("게시판 수정 실패");
				forward.setPath("BoardModifyView.bo");
				request.setAttribute("message", "게시판 수정 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}

			System.out.println("게시판 등록 완료");

			// 글 등록이 완료되면 게시판 리스트로 이동
			forward.setRedirect(true);
			forward.setPath("BoardList.bo");
			return forward;

		} catch (IOException ex) {
			forward.setPath("BoardModifyView.bo");
			request.setAttribute("message", "게시판 수정 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
	}

}
