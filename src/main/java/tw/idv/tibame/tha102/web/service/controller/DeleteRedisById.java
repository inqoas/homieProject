package tw.idv.tibame.tha102.web.service.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.Jedis;
import tw.idv.tibame.tha102.web.product.vo.Product;
import tw.idv.tibame.tha102.web.service.vo.Service;
@WebServlet(urlPatterns = "/Service/DeleteRedisById")
public class DeleteRedisById extends HttpServlet{
	
	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		gson =new Gson();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("Appliction/json; charset=utf-8");
		
		String user_id   	 =req.getParameter("user_id");
		String product_id	 =req.getParameter("Service_id").trim();
		String product_price =req.getParameter("Service_price").trim();
		String product_count =req.getParameter("Service_count").trim();
		String product_name  =req.getParameter("Service_name").trim();
		String product_total =req.getParameter("Service_total").trim();
		
		StringBuilder use_service =new StringBuilder(user_id).append("_Service");
		
		Service service =new Service();
		service.setService_id(Integer.parseInt(product_id));
		service.setService_price(Integer.parseInt(product_price));
		service.setService_count(Integer.parseInt(product_count));
		service.setService_name(product_name);
		service.setService_total(Integer.parseInt(product_total));
		
		try(Jedis jedis =new Jedis()){
			
			List<String> jediss = jedis.lrange(use_service.toString(), 0, jedis.llen(use_service.toString()));
			for(String strs : jediss) {
				
				Service jedisPro = gson.fromJson(strs,Service.class);
				
				if(jedisPro.getService_id() == service.getService_id()) {
					jedis.lrem(use_service.toString(), 0, strs);
				}
			}
		}
		
		resp.getWriter().write(gson.toJson(service));
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	
}
