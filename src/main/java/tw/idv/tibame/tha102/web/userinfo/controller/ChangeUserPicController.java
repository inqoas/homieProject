package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tw.idv.tibame.tha102.core.util.UserInfoJwtUtil;
import tw.idv.tibame.tha102.web.userinfo.service.MemberService;
import tw.idv.tibame.tha102.web.userinfo.service.impl.MemberServiceImpl;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;
@Controller
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1 MB
	    maxFileSize = 1024 * 1024 * 20, // 20 MB
	    maxRequestSize = 1024 * 1024 * 50 // 50 MB
	)
@WebServlet("/changeUserPic")
public class ChangeUserPicController extends HttpServlet{
	private MemberService memberServiceImpl;
	private UserInfoJwtUtil jwtUtil;
	@Autowired
	public ChangeUserPicController(UserInfoJwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	@Override
	public void init() throws ServletException {
		memberServiceImpl = new MemberServiceImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		String jwtString = req.getHeader("Authorization");
		int user_id = jwtUtil.checkUserInfoJwt(jwtString);
		Part file = req.getPart("file");
		byte[] pic = file.getInputStream().readAllBytes();
		UserInfo userInfo = new UserInfo();
		userInfo.setUser_pic(pic);
		userInfo = memberServiceImpl.changeUserPic(userInfo, user_id);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		try (PrintWriter writer = resp.getWriter()){
			writer.print(gson.toJson(userInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
