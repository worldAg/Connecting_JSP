package my.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import action.Action;
import action.ActionForward;
import bo.board.db.BoardDAO;
import bo.board.db.HeartDAO;

// 마이페이지에 작성글, 관심글 리스트 json으로 전송
public class MyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		BoardDAO boardDao = new BoardDAO();
		HeartDAO heartDao = new HeartDAO();
		JsonObject object = new JsonObject();
		
		JsonArray boardlist = boardDao.getMyBoard(id);
		JsonArray heartlist = heartDao.getMyHeartList(id);
		JsonElement mylist = new Gson().toJsonTree(boardlist);
		JsonElement myheart = new Gson().toJsonTree(heartlist);
		
		object.add("mylist", mylist);
		object.add("myheart", myheart);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(object.toString());
		System.out.println("ajax 결과: " + object.toString());
		return null;
		
	}

}