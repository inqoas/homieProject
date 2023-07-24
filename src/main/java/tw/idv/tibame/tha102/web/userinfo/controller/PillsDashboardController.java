package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/pills-dashboard")
public class PillsDashboardController extends HttpServlet{
	private static final long serialVersionUID = 4801940728190218546L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Integer userId = (Integer)session.getAttribute("user_id");
	}
}
