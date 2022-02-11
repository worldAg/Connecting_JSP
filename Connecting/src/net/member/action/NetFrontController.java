package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.net")
public class NetFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    		String RequestURI = request.getRequestURI();
    		System.out.println("RequestURI = " + RequestURI);
    	
    		String contextPath = request.getContextPath();
    		System.out.println("contextPath = " + contextPath);
    		
    		String command = RequestURI.substring(contextPath.length());
    		System.out.println("command = " + command);
    		
    		
    		ActionForward forward = null;
    		Action action = null;
    		
    		switch (command) {
    		case "/register.net":
    			action = new MemberJoinAction();
    			break;
    		case "/idcheck.net":
    			action = new MemberIdCheckAction();
    			break;
    		case "/login.net":
    			action = new MemberLoginAction();
    			break;
    		case "/joinProcess.net":
    			action = new MemberJoinProcessAction();
    			break;
    		case "/loginProcess.net":
    			action = new MemberLoginProcessAction();
    			break;
    		case "/logout.net":
    			action = new MemberLogoutAction();
    			break;
    		case "/emailcheck.net":
    			action = new EmailCheckAction();
    			break;
    		case "/idfind.net":
    			action = new IdfindAction();
    			break;
    		case "/idfindProcess.net":
    			action = new IdfindProcessAction();
    			break;
    		case "/pwfind.net":
    			action = new PwfindAction();
    			break;
    		case "/pwfindProcess.net":
    			action = new PwfindProcessAction();
    			break;
		/*
		 * case "/sendmail.net": action = new SendmailAction(); break;
		 */
    		case "/naverlogin.net":
    			action = new NaverloginAction();
    			break;
    		case "/naverloginProcess.net":
    			action = new NaverloginProcessAction();
    			break;
    		} 
    		forward = action.execute(request, response);
    		
    		if(forward != null) {
    			if (forward.isRedirect()) {
    				response.sendRedirect(forward.getPath());
    			} else { 
    				RequestDispatcher dispatcher = 
    						request.getRequestDispatcher(forward.getPath());
    				dispatcher.forward(request, response);
    			}
    		}
    	}
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		doProcess(request, response);
    		
    	}
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		request.setCharacterEncoding("utf-8");
    		doProcess(request, response);
    	}

    }