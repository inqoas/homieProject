package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.core.util.UserInfoJwtUtil;
import tw.idv.tibame.tha102.web.userinfo.service.MemberService;
import tw.idv.tibame.tha102.web.userinfo.service.impl.MemberServiceImpl;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;
@Controller
@WebServlet("/changeUserInfo")
public class ChangeUserInfoController extends HttpServlet{
	private static final long serialVersionUID = -6339057283941959340L;
	private MemberService memberServiceImpl;
	private UserInfoJwtUtil userInfoJwtUtil;
	@Autowired
	public ChangeUserInfoController(UserInfoJwtUtil userInfoJwtUtil) {
		this.userInfoJwtUtil = userInfoJwtUtil;
	}
	@Override
	public void init() throws ServletException {
		memberServiceImpl = new MemberServiceImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jwtString = req.getHeader("Authorization");
		int user_id = userInfoJwtUtil.checkUserInfoJwt(jwtString);
		Gson gson = new Gson();
		UserInfo userInfo = gson.fromJson(req.getReader(), UserInfo.class);
		if(user_id == userInfo.getUser_id()) {
			userInfo = memberServiceImpl.changeUserInfo(userInfo);
		}else {
			userInfo.setMessage("會員驗證錯誤請重新登入");
			userInfo.setSuccess(false);
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		try (PrintWriter writer = resp.getWriter()){
			writer.print(gson.toJson(userInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
