package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import tw.idv.tibame.tha102.web.userinfo.service.MemberService;
import tw.idv.tibame.tha102.web.userinfo.service.impl.MemberServiceImpl;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;


@WebServlet("/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = -3245570107853379659L;
	private MemberService memberService;
	
	@Override
	public void init() throws ServletException {
		memberService = new MemberServiceImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		UserInfo userInfo = gson.fromJson(req.getReader(), UserInfo.class);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		if(userInfo == null) {
			userInfo = new  UserInfo();
			userInfo.setMessage("無會員資訊");
			userInfo.setSuccess(false);
			try (PrintWriter writer = resp.getWriter()){
				writer.print(gson.toJson(userInfo));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		userInfo = memberService.login(userInfo);
		if(userInfo.isSuccess()) {
			if (req.getSession(false) != null) {
				req.changeSessionId();
			}
			HttpSession session = req.getSession();
			session.setAttribute("user_id", userInfo.getUser_id());
			session.setAttribute("user_name", userInfo.getUser_name());
			session.setAttribute("seller_identity", userInfo.getSeller_identity());
			session.setAttribute("user_status", userInfo.getUser_status());
		}
		
		try (PrintWriter writer = resp.getWriter()){
			writer.print(gson.toJson(userInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
