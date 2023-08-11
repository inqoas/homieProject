package tw.idv.tibame.tha102.web.service.controller;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.service.dao.ServiceDao;
import tw.idv.tibame.tha102.web.service.dao.Imp.ServiceDaoImpl;
import tw.idv.tibame.tha102.web.service.vo.Service;
@WebServlet(urlPatterns="/service/Select_byIdController")
public class Select_byIdController extends HttpServlet{
	
	private ServiceDao servicedao;
	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		servicedao =new ServiceDaoImpl();
		gson =new Gson();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("appliction/json; charset=utf-8 ");
		
		Service service = gson.fromJson(req.getReader(), Service.class);
		
		service = servicedao.Select_byId(service.getService_id());
		
		resp.getWriter().write(gson.toJson(service));
		
	}
}
