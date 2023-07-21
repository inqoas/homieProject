package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;


@WebServlet("/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = -3245570107853379659L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		UserInfo userInfo = gson.fromJson(req.getReader(), UserInfo.class);
		System.out.println(userInfo.getUser_account());
		System.out.println(userInfo.getUser_password());
	}
}
