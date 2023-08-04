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
@WebServlet("/changeUserPassword")
public class ChangeUserPasswordController extends HttpServlet{
	private static final long serialVersionUID = 6134219820601521855L;
	private MemberService memberServiceImpl;
	private UserInfoJwtUtil jwtUtil;
	@Autowired
	public ChangeUserPasswordController(UserInfoJwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	@Override
	public void init() throws ServletException {
		memberServiceImpl = new MemberServiceImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		String jwString = req.getHeader("Authorization");
		int userid = jwtUtil.checkUserInfoJwt(jwString);
		UserInfo userInfo = gson.fromJson(req.getReader(), UserInfo.class);
		userInfo = memberServiceImpl.changeUserPassword(userInfo, userid);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		try (PrintWriter writer = resp.getWriter()){
			writer.print(gson.toJson(userInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
