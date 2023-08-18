package tw.idv.tibame.tha102.web.service.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import static tw.idv.tibame.tha102.core.util.CommonMysql.LOCALHOST;
import static tw.idv.tibame.tha102.core.util.CommonMysql.PORT;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.Jedis;
import tw.idv.tibame.tha102.web.emp.vo.Emp;
import tw.idv.tibame.tha102.web.orderproduct.dao.OrderProductDaoImpl;
import tw.idv.tibame.tha102.web.product.dao.ProductDaoImpl;
import tw.idv.tibame.tha102.web.product.vo.Product;
import tw.idv.tibame.tha102.web.service.vo.Service;
@WebServlet(urlPatterns = "/Service/GetAllRedisController")
public class GetAllRedisController extends HttpServlet{
	
	private Gson gson;
	
	@Override
	public void init() throws ServletException {		
		super.init();
		gson = new Gson();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("Appliction/json; charset=utf-8");
		
		String user_id =req.getParameter("user_id");
		
		StringBuilder stringBuilder = new StringBuilder(user_id).append("_Service");
		
		List<Service> service =new ArrayList();
		
		try(Jedis jedis =new Jedis(LOCALHOST,PORT)){
			
			List<String> ss = jedis.lrange(stringBuilder.toString(),0,jedis.llen(stringBuilder.toString()));
			
			for(String str : ss) {
				
				Service empre =gson.fromJson(str, Service.class);
				
				service.add(empre);
			}
			
			resp.getWriter().write(gson.toJson(service));	
			
			
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
