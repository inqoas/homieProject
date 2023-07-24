package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.userinfo.service.MemberService;
import tw.idv.tibame.tha102.web.userinfo.service.impl.MemberServiceImpl;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;

@WebServlet("/signup")
public class MemberSignUpController extends HttpServlet{

	private static final long serialVersionUID = 7280473218269079653L;
	private MemberService memberServiceImpl;
	@Override
	public void init() throws ServletException {
		memberServiceImpl = new MemberServiceImpl();
	};
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new GsonBuilder()
			    .setDateFormat("yyyy-MM-dd")
			    .create();
		
		UserInfo userInfo = gson.fromJson(req.getReader(), UserInfo.class);
		userInfo = memberServiceImpl.register(userInfo);		
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		try (PrintWriter writer =  resp.getWriter()){
			writer.print(gson.toJson(userInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
