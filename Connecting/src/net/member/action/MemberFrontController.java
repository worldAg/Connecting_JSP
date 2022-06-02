package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;

@WebServlet("*.net")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
 
		String RequestURI = request.getRequestURI();
    	System.out.println("RequestURI = " + RequestURI);	// ex. /Connecting/login.net
    	
    	String contextPath = request.getContextPath();
    	System.out.println("contextPath = " + contextPath);	// ex. /Connecting
    		
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println("command = " + command);			// ex. /login.net
    		
    	// 초기화
    	Action action = null;
    	ActionForward forward = null;
    		
    	switch (command) {
	    	case "/login.net":
	    		action = new LoginAction();
	    		break;
	    	case "/loginProcess.net":
	    		action = new LoginProcessAction();
	    		break;
	    	case "/logout.net":
	    		action = new LogoutAction();
	    		break;
    		case "/register.net":
    			action = new MemberJoinAction();
    			break;
    		case "/joinProcess.net":
    			action = new MemberJoinProcessAction();
    			break;
    		case "/idcheck.net":
    			action = new MemberIdCheckAction();
    			break;
    		case "/emailcheck.net":
    			action = new MemberEmailCheckAction();
    			break;
    		case "/idfind.net":
    			action = new MemberIdfindView();
    			break;
    		case "/idfindProcess.net":
    			action = new MemberIdfindAction();
    			break;
    		case "/pwfind.net":
    			action = new MemberPwfindView();
    			break;
    		case "/pwfindProcess.net":
    			action = new MemberPwfindAction();
    			break;
    		case "/naverlogin.net":
    			action = new NaverloginAction();
    			break;
    		case "/naverloginProcess.net":
    			action = new NaverloginProcessAction();
    			break;
    		case "/mypage.net":
    			action = new MemberInfoAction();
    			break;
    		case "/memberModify.net":
    			action = new MemberUpdateAction();
    			break;
    		case "/memberModifyAction.net":
    			action = new MemberUpdateProcessAction();
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