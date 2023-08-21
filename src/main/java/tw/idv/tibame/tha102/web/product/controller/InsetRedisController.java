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
		
		String user_id="user_id:1";
							
		Product product =gson.fromJson(req.getReader(),Product.class);
				
		String jedisSev = new JSONObject(product).toString();
		
		//StringBuilder str =new StringBuilder(user_id).append("Product_Id"+product.getProduct_id());

		try (Jedis jedis = new Jedis(LOCALHOST, PORT);) {

			List<String> rediss = jedis.lrange(user_id, 0, jedis.llen(user_id));
			
			if (rediss.size() == 0) {
				
				jedis.lpush(user_id, jedisSev);
				
			} else {
				for (String strs : rediss) {

					Product redstr = gson.fromJson(strs, Product.class);

					if (redstr.getProduct_id() == product.getProduct_id()) {
						// Integer count =redstr.getProduct_count()+product.getProduct_count();
						product.setProduct_count(redstr.getProduct_count() + product.getProduct_count());

						product.setProduct_total(redstr.getProduct_total() + product.getProduct_total());

						jedisSev = new JSONObject(product).toString();
						// 先刪除在增加
						jedis.del(user_id, strs);

						jedis.lpush(user_id, jedisSev);
						
						break;
						
					} else {

						jedis.lpush(user_id, jedisSev);
						
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
