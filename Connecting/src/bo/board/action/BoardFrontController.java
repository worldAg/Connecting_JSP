package bo.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 요청된 전체 url 중에서 포트 번호 다음 부터 마지막 문자열까지 반환합니다.
		 * 		예) contextPath가 "/JspProject"인 경우,
		 * 		http://localhost:8088/JspProject/login.net으로 요청하면, RequestURI는
		 * 			"/JspProject/login.net"이 반환됩니다.
		 */
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI: " + RequestURI);
		
		// getContextPath() : 컨텍스트 경로가 반환된다.
		// contextPath는 "/JspProject"가 반환된다.
		String contextPath = request.getContextPath();
		System.out.println("contextPath: " + contextPath);
		
		/*
		 * RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터,
		 * 마지막 위치 문자까지 추출한다.
		 * command는 "/login.net"이 반환된다.
		 */
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command: " + command);
		
		
		// 초기화
		Action action = null;
		
		switch (command) {			
			case "/BoardList.bo": // 로그인이 성공적으로 됐을 때 이 주소로 "redirect"돼서 이동. 즉 주소가 바뀜. 그리고 boardList.jsp로 forward
				action = new BoardListAction();
				break;	
			case "/NoticeList.bo":
				action = new NoticeListAction();
				break;
			case "/BoardDetailAction.bo":
				action = new BoardDetailAction();
				break;
		} // switch ends
		
		ActionForward forward = action.execute(request, response);
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	} // doProcess ends

}
