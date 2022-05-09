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
import bo.board.db.BoardBean;
import bo.board.db.BoardDAO;

public class BoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		ActionForward forward = new ActionForward();

		String realFolder = "";
		String saveFolder = "boardupload";
		int fileSize = 5 * 1024 * 1024;

		// 실제 저장 경로 지정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder = " + realFolder);
		boolean result = false;
		
		HttpSession session = request.getSession(); 
		String id = (String) session.getAttribute("id");
		
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8",
					new DefaultFileRenamePolicy());
			
			
			boarddata.setCategory(Integer.parseInt(multi.getParameter("category")));
			boarddata.setLoc(Integer.parseInt(multi.getParameter("loc")));
			boarddata.setId(id);
			boarddata.setTitle(multi.getParameter("title"));
			boarddata.setHost_name(multi.getParameter("host_name"));
			boarddata.setAddress(multi.getParameter("address"));
			boarddata.setStart_date(multi.getParameter("start_date"));
			boarddata.setEnd_date(multi.getParameter("end_date"));
			boarddata.setStart_time(multi.getParameter("start_time"));
			boarddata.setEnd_time(multi.getParameter("end_time"));
			boarddata.setContent(multi.getParameter("content"));

			String filename = multi.getFilesystemName("board_img");
			boarddata.setBoard_img(filename);

			result = boarddao.boardInsert(boarddata);

			// 글 등록에 실패할 경우
			if (result == false) {
				System.out.println("게시판 등록 실패");
				forward.setPath("boardWriteAction.bo");
				request.setAttribute("message", "게시판 등록 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}

			System.out.println("게시판 등록 완료");

			// 글 등록이 완료되면 글 상세페이지로 이동
			forward.setRedirect(true);
			forward.setPath("BoardList.bo");
			return forward;

		} catch (IOException ex) {
			forward.setPath("boardWrite.bo");
			request.setAttribute("message", "게시판 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}

	}

}
