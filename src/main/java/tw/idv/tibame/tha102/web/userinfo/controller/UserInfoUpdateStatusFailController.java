package tw.idv.tibame.tha102.web.userinfo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.userinfo.service.UserInfoService;
import tw.idv.tibame.tha102.web.userinfo.service.impl.UserInfoServiceImpl;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/userinfo/update-status-fail")
public class UserInfoUpdateStatusFailController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private UserInfoService userInfoService;

    public UserInfoUpdateStatusFailController(){
        userInfoService = new UserInfoServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");

        try {
            String userIdString = request.getParameter("user_id");
            Integer userId = Integer.parseInt(userIdString);
            userInfoService.sellerFail(userId);
            response.getWriter().write("{\"message\": \"設定成功\"}");
        } catch (NumberFormatException e) {
            sendError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid user_id format");
        }
    }

    private void sendError(HttpServletResponse response, int statusCode, String errorMessage) throws IOException {
        response.setStatus(statusCode);
        response.getWriter().write(errorMessage);
    }
}


