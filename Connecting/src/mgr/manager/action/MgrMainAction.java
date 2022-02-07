package mgr.manager.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MgrMainAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String go = request.getParameter("page");
		if(go == null) {
			go = "mgr_main";
		}
		
		request.setAttribute("pagefile", go);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); 
		forward.setPath("mgr_page.jsp");
		return forward;
	}

}
