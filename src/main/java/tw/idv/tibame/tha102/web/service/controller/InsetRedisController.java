package tw.idv.tibame.tha102.web.service.controller;

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
import tw.idv.tibame.tha102.web.Redis.vo.Redis_Data_Service;
import tw.idv.tibame.tha102.web.product.vo.Product;
import tw.idv.tibame.tha102.web.service.dao.ServiceDao;
import tw.idv.tibame.tha102.web.service.dao.Imp.ServiceDaoImpl;
import tw.idv.tibame.tha102.web.service.vo.Service;
import tw.idv.tibame.tha102.web.userinfo.dao.Impl.ServiceCollectionDaoImpl;
@WebServlet(urlPatterns ="/Service/InsetRedisController" )
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

		String user_id = "user_id:1";

		Redis_Data_Service Redis_Data_Service = gson.fromJson(req.getReader(), Redis_Data_Service.class);

		String jedisSev = new JSONObject(Redis_Data_Service).toString();

		StringBuilder str = new StringBuilder(user_id).append("_Service");

		try (Jedis jedis = new Jedis(LOCALHOST, PORT);) {

			List<String> rediss = jedis.lrange(str.toString(), 0, jedis.llen(str.toString()));
			if (rediss.size() == 0) {
				jedis.lpush(str.toString(), jedisSev);
			} else {
				for (String strs : rediss) {

					Redis_Data_Service redstr = gson.fromJson(strs, Redis_Data_Service.class);

					if (redstr.getService_id() == Redis_Data_Service.getService_id()) {

						// Integer count =redstr.getProduct_count()+product.getProduct_count();

						Redis_Data_Service.setService_count(redstr.getService_count() + Redis_Data_Service.getService_count());
						Redis_Data_Service.setService_total(redstr.getService_total() + Redis_Data_Service.getService_total());

						jedisSev = new JSONObject(Redis_Data_Service).toString();

						// 先刪除在增加
						jedis.del(str.toString(), strs);

						jedis.lpush(str.toString(), jedisSev);
						
						break;
					} else {

						jedis.lpush(str.toString(), jedisSev);
						
						break;

					}
				}
			}
		}
		
		resp.getWriter().write(gson.toJson(Redis_Data_Service));
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
