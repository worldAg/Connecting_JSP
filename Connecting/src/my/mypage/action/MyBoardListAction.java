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
import net.member.db.Member;
import net.member.db.MemberDAO;

public class MyBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		BoardDAO boardDao = new BoardDAO();
		JsonObject object = new JsonObject();
		JsonArray jarray = boardDao.getMyBoard(id);
		JsonElement mylist = new Gson().toJsonTree(jarray);
		object.add("mylist", mylist);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(object.toString());
		System.out.println("ajax 결과: " + object.toString());
		return null;
	}

}