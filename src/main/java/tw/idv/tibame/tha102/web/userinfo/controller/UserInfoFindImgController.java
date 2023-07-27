package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.userinfo.dao.UserInfoDaoImpl;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;

@WebServlet(urlPatterns = "/userinfo/userInfoFindImgController")
public class UserInfoFindImgController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserInfoDaoImpl userInfoDaoImpl;
	
	@Override
	public void init() throws ServletException {
		userInfoDaoImpl = new UserInfoDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json; charset=utf-8");
		
		resp.setContentType("image/jpg");
		ServletOutputStream out = resp.getOutputStream();
		
		Integer id =Integer.parseInt(req.getParameter("user_id"));
		
		UserInfo userInfo =userInfoDaoImpl.getUserPicById(id);
		
		InputStream input =new ByteArrayInputStream(userInfo.getUser_pic());
		
		BufferedInputStream in = new BufferedInputStream(input);
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
		
	}
}
