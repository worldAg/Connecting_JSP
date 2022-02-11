package mgr.manager.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.mgr")
public class MgrFrontController extends HttpServlet {
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
    	case "/mgrMain.mgr" :
    		action = new MgrMainAction();
    		break;
    	case "/memberList.mgr" :
    		action = new MemberListAction();
    		break;
    	case "/memberDelete.mgr" :
			action = new MemberDeleteAction();
			break;
    	case "/memberInfo.mgr" :
    		action = new MemberInfoAction();
    		break;
    	case "/noticeList.mgr" :
    		action = new NoticeListAction();
    		break;
    	case "/noticeAddAction.mgr" :
    		action = new NoticeAddAction();
    		break;
    	case "/noticeDelete.mgr" :
    		action = new NoticeDeleteAction();
    		break;
    	case "/noticeWrite.mgr" :
    		action = new NoticeWriteView();
    		break;
    	case "/noticeDetail.mgr" :
    		action = new NoticeDetailAction();
    		break;
    	case "/noticeModifyView.mgr" :
    		action = new NoticeModifyView();
    		break;
    	case "/noticeModifyAction.mgr" :
    		action = new NoticeModifyAction();
    		break;
    	
    	}
   
    	forward = action.execute(request, response);
    	
    	if (forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());  			
    		} else { 
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
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
