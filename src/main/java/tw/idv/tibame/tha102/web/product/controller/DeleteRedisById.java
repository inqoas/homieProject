package tw.idv.tibame.tha102.web.product.controller;

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
@WebServlet(urlPatterns = "/Product/DeleteRedisById")
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

		String product_id	 =req.getParameter("product_id").trim();
		String product_price =req.getParameter("product_price").trim();
		String product_count =req.getParameter("product_count").trim();
		String product_name  =req.getParameter("product_name").trim();
		String product_total =req.getParameter("product_total").trim();
		
		Product product =new Product();
		product.setProduct_id(Integer.parseInt(product_id));
		product.setProduct_price(Integer.parseInt(product_price));
		product.setProduct_count(Integer.parseInt(product_count));
		product.setProduct_name(product_name);
		product.setProduct_total(Integer.parseInt(product_total));
		
		try(Jedis jedis =new Jedis()){
			
			List<String> jediss = jedis.lrange(user_id, 0, jedis.llen(user_id));
			for(String strs : jediss) {
				
				Product jedisPro = gson.fromJson(strs,Product.class);
				
				if(jedisPro.getProduct_id() == product.getProduct_id()) {
					jedis.lrem(user_id, 0, strs);
				}
			}
		}
		
		resp.getWriter().write(gson.toJson(product));
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	
}
