package bo.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;


@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI: " + RequestURI);	// ex. /Connecting/BoardList.bo
	
		String contextPath = request.getContextPath();
		System.out.println("contextPath: " + contextPath);	// ex. /Connecting
		
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command: " + command);			// ex. /BoardList.bo
		
		// 초기화
		Action action = null;
		ActionForward forward = null;
		
		switch (command) {			
			case "/BoardList.bo": 
				action = new BoardListAction();
				break;
			case "/BoardSearchBarListAction.bo":
				action = new BoardSearchBarListAction();
				break;
			case "/BoardDetailAction.bo":
				action = new BoardDetailAction();
				break;
			case "/IsAddedToMemberTable.bo":
				action = new IsAddedToMemberTableAction();
				break;
			case "/AddOrRemoveHeart.bo":
				action = new AddOrRemoveHeartAction();
				break;				
			case "/boardAddAction.bo" :
	    		action = new BoardAddAction();
	    		break;
	    	case "/boardWrite.bo" :
	    		action = new BoardWriteAction();
	    		break;
	    	case "/BoardModifyView.bo": 
	    		action = new BoardModifyView(); 
	    		break; 
	    	case "/BoardModifyAction.bo": 
	    		action = new BoardModifyAction(); 
	    		break; 
	    	case "/BoardDelete.bo":
	    		action = new BoardDeleteAction(); 
	    		break;
	    	case "/RecentBoard.bo":
	    		action = new RecentBoardAction();
	    		break;
		} // switch ends
		
		forward = action.execute(request, response);
		
		if (action != null) {
			
			if (forward != null) {
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getPath());
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				}
			}		
		}
	} // doProcess ends

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
	
}
