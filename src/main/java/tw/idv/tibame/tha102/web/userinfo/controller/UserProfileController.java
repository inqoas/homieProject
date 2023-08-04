package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tw.idv.tibame.tha102.core.util.UserInfoJwtUtil;
import tw.idv.tibame.tha102.web.userinfo.service.UserDashboardService;
import tw.idv.tibame.tha102.web.userinfo.service.impl.UserDashboardServiceImpl;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;
@Controller
@WebServlet("/user-profile")
public class UserProfileController extends HttpServlet {
	private static final long serialVersionUID = -1359326233505038780L;
	private UserDashboardService userDashboardService;
	private UserInfoJwtUtil userInfoJwtUtil;
	@Autowired
	public UserProfileController(UserInfoJwtUtil userInfoJwtUtil) {
		this.userInfoJwtUtil = userInfoJwtUtil;
	}
	@Override
	public void init() throws ServletException {
		userDashboardService = new UserDashboardServiceImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
			    .create();
		String jwt = req.getHeader("Authorization");
		int user_id = userInfoJwtUtil.checkUserInfoJwt(jwt);
		UserInfo userInfo = new UserInfo();
		if(user_id == 0) {
			userInfo.setSuccess(false);
		}
		userInfo =  userDashboardService.userProfile(user_id);
		byte[] user_pic =  userInfo.getUser_pic();
		if(user_pic != null) {
			String user_pic_base64 = Base64.getEncoder().encodeToString(user_pic);
			userInfo.setUser_pic(null);
			userInfo.setUser_pic_base64(user_pic_base64);
		}

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		try (PrintWriter writer = resp.getWriter();){
			writer.print(gson.toJson(userInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
