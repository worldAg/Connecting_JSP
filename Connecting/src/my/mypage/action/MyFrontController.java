package my.mypage.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import my.mypage.action.MemberInfoAction;
import my.mypage.action.MemberUpdateAction;
import my.mypage.action.MemberUpdateProcessAction;

@WebServlet("*.my")
public class MyFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
 
		String RequestURI = request.getRequestURI();
    	System.out.println("RequestURI = " + RequestURI);	// ex. /Connecting/mypage.my
    	
    	String contextPath = request.getContextPath();
    	System.out.println("contextPath = " + contextPath);	// ex. /Connecting
    		
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println("command = " + command);			// ex. /mypage.my
    		
    	// 초기화
    	Action action = null;
    	ActionForward forward = null;
    		
    	switch (command) {
    		case "/mypage.my":
    			action = new MemberInfoAction();
    			break;
    		case "/memberModify.my":
    			action = new MemberUpdateAction();
    			break;
    		case "/memberModifyAction.my":
    			action = new MemberUpdateProcessAction();
    			break;
    		case "/mylist.my":
    			action = new MyBoardListAction();
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