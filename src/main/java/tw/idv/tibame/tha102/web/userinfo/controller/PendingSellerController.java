package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.userinfo.service.UserInfoService;
import tw.idv.tibame.tha102.web.userinfo.service.UserInfoServiceImpl;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;

@WebServlet("/userinfo/find-pending-seller")
public class PendingSellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoService userInfoService;
    private static final Gson gson = new Gson();
    
    public PendingSellerController() {
    	userInfoService = new UserInfoServiceImpl();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json; charset=utf-8");

        List<UserInfo> userList = userInfoService.findPendingSeller();

        String jsonResult = gson.toJson(userList);
        response.getWriter().write(jsonResult);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    
}
