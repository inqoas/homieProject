package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.core.util.UserInfoJwtUtil;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;
@WebServlet(urlPatterns = "/userinfo/CheckMemberController")
public class CheckMemberController extends HttpServlet{
@Autowired	
	private UserInfoJwtUtil userInfoJwtUtil;

	private Gson gson =new Gson();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jwt_user = req.getHeader("Authorization");
		
		int user_id = userInfoJwtUtil.checkUserInfoJwt(jwt_user);
		
		resp.getWriter().write(String.valueOf(user_id));
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
}
