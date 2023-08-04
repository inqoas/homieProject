package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;
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
@WebServlet("/header")
public class MemberHeaderController extends HttpServlet{
	private static final long serialVersionUID = -2876275431868375104L;
	private MemberService memberServiceImpl;
	private UserInfoJwtUtil userInfoJwtUtil;
	
	@Autowired
	public MemberHeaderController(UserInfoJwtUtil userInfoJwtUtil) {
		this.userInfoJwtUtil = userInfoJwtUtil;
	}
	
	@Override
	public void init() throws ServletException {
		memberServiceImpl = new MemberServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jwt = req.getHeader("Authorization");
		int user_id =  userInfoJwtUtil.checkUserInfoJwt(jwt);
		UserInfo userInfo = new UserInfo();
		userInfo =  memberServiceImpl.header(user_id);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		try (PrintWriter writer = resp.getWriter()){
			writer.print(new Gson().toJson(userInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
