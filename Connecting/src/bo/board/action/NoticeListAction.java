package bo.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import bo.board.db.NoticeBean;
import bo.board.db.NoticeDAO;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		NoticeDAO dao = new NoticeDAO();
		List<NoticeBean> noticelist = dao.getAllNotices();
		
		if (noticelist != null) {
			JsonObject object = new JsonObject();
			object.addProperty("listcount", noticelist.size()); // 총 글의 개수
			
			/*
			 * JsonObject에 List 형식을 담을 수 있는 addProperty 메서드는 없음
			 * void com.google.gson.JsonObject.add(String property, JsonElement value) 메서드를 이용하여 저장해야 함
			 * List 형식을 JsonElement로 바꿔 줘야만 object에 저장할 수 있음
			 * List => JsonElement
			 */
			
			JsonElement je = new Gson().toJsonTree(noticelist);
			System.out.println("JsonElement type - je.toString(): \n" + je.toString());
			object.add("noticelist", je);
			
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append(object.toString());
			System.out.println("Java에서 JSP/AJAX로 보내는 결과 JsonObject의 toString(): \n" + object.toString());
		} else {
			
		}
		return null;
	}

}
