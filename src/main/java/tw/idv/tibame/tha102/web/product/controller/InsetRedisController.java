package tw.idv.tibame.tha102.web.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;

import static tw.idv.tibame.tha102.core.util.CommonMysql.LOCALHOST;
import static tw.idv.tibame.tha102.core.util.CommonMysql.PORT;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.Jedis;
import tw.idv.tibame.tha102.web.product.vo.Product;
import tw.idv.tibame.tha102.web.service.dao.ServiceDao;
import tw.idv.tibame.tha102.web.service.dao.Imp.ServiceDaoImpl;
import tw.idv.tibame.tha102.web.service.vo.Service;
import tw.idv.tibame.tha102.web.userinfo.dao.Impl.ServiceCollectionDaoImpl;
@WebServlet(urlPatterns ="/Product/InsetRedisController" )
public class InsetRedisController extends HttpServlet{
	
	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		super.init();
		gson =new Gson();

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json; charset=utf-8");
		
//		  user_id:data,
//          product_id:product_id.innerHTML,
//          product_name :product_name.innerHTML,
//          product_count:qtyInput.value,
//          product_price:product_price,
//          product_total:product_total,
							
		String user_id =req.getParameter("user_id");
		String product_id =req.getParameter("product_id");
		String product_name =req.getParameter("product_name");
		String product_count =req.getParameter("product_count");
		String product_price =req.getParameter("product_price");
		String product_total =req.getParameter("product_total");
		
		System.out.println(product_id);
		
		Product product =new Product(); 
		
		product.setProduct_id(Integer.parseInt(product_id));
		product.setProduct_name(product_name);
		product.setProduct_count(Integer.parseInt(product_count));
		product.setProduct_price(Integer.parseInt(product_price));
		product.setProduct_total(Integer.parseInt(product_total));
	
		
		String jedisSev = new JSONObject(product).toString();
		
		StringBuilder stringBuilder = new StringBuilder(user_id).insert(0, "user_id:");

		try (Jedis jedis = new Jedis(LOCALHOST, PORT);) {

			List<String> rediss = jedis.lrange(stringBuilder.toString(), 0, jedis.llen(stringBuilder.toString()));
			
			if (rediss.size() == 0) {
				
				jedis.lpush(stringBuilder.toString(), jedisSev);
				
			} else {
				for (String strs : rediss) {

					Product redstr = gson.fromJson(strs, Product.class);

					if (redstr.getProduct_id() == product.getProduct_id()) {
						// Integer count =redstr.getProduct_count()+product.getProduct_count();
						product.setProduct_count(redstr.getProduct_count() + product.getProduct_count());

						product.setProduct_total(redstr.getProduct_total() + product.getProduct_total());

						jedisSev = new JSONObject(product).toString();
						// 先刪除在增加
						jedis.del(stringBuilder.toString(), strs);

						jedis.lpush(stringBuilder.toString(), jedisSev);
						
						break;
						
					} else {

						jedis.lpush(stringBuilder.toString(), jedisSev);
						
						break;
					}

				}
			}
		}
		
		resp.getWriter().write(gson.toJson(product));
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
