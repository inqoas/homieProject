package tw.idv.tibame.tha102.web.service.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.Jedis;
import tw.idv.tibame.tha102.web.Redis.vo.Redis_Data_Service;
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
		String service_id	 =req.getParameter("service_id").trim();
		String service_name  =req.getParameter("service_name").trim();
		String service_price =req.getParameter("service_price").trim();
		String service_count =req.getParameter("service_count").trim();
		String service_id_code=req.getParameter("service_id_code");
		String seller_id     =req.getParameter("seller_id");
		String service_DateDetail =req.getParameter("service_DateDetail");
		String service_Date  =req.getParameter("service_Date").trim();
		String service_total =req.getParameter("service_total");
			
		StringBuilder use_service =new StringBuilder(user_id).append("_Service");
		
		Redis_Data_Service redis_data_service =new Redis_Data_Service();
		
		redis_data_service.setService_id(Integer.parseInt(service_id));
		redis_data_service.setService_name(service_name);
		redis_data_service.setService_price(Integer.parseInt(service_price));
		redis_data_service.setService_count(Integer.parseInt(service_count));
		redis_data_service.setService_id_code(Integer.parseInt(service_id_code));
		redis_data_service.setSeller_id(Integer.parseInt(seller_id));
		redis_data_service.setService_DateDetail(Integer.parseInt(service_DateDetail));
		redis_data_service.setService_Date(service_Date);
		redis_data_service.setService_total(Integer.parseInt(service_total));
		
		try(Jedis jedis =new Jedis()){
			
			List<String> jediss = jedis.lrange(use_service.toString(), 0, jedis.llen(use_service.toString()));
			for(String strs : jediss) {
				
				Redis_Data_Service jedisPro = gson.fromJson(strs,Redis_Data_Service.class);
				
				if(jedisPro.getService_id() == redis_data_service.getService_id()) {
					jedis.lrem(use_service.toString(), 0, strs);
				}
			}
		}
		
		resp.getWriter().write(gson.toJson(redis_data_service));
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	
}
