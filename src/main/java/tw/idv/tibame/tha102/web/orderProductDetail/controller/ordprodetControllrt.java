package tw.idv.tibame.tha102.web.orderProductDetail.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.Jedis;
import tw.idv.tibame.tha102.web.Redis.vo.JsonList;
import tw.idv.tibame.tha102.web.Redis.vo.Redis_Data;
import tw.idv.tibame.tha102.web.Redis.vo.Redis_Data_Service;
import tw.idv.tibame.tha102.web.orderProductDetail.dao.OrderProductDetailDao;
import tw.idv.tibame.tha102.web.orderProductDetail.dao.OrderProductDetailDaoImpl;
import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;
import tw.idv.tibame.tha102.web.orderproduct.dao.OrderProductDao;
import tw.idv.tibame.tha102.web.orderproduct.dao.OrderProductDaoImpl;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;
@WebServlet(urlPatterns = "/OrderProdect/ordprodetControllrt")
public class ordprodetControllrt extends HttpServlet{

	private Gson gson;
	private OrderProduct orderproduct;
	private OrderProductDetailDao orderproductdetailDao;
	
	@Override
	public void init() throws ServletException {
		gson =new Gson();
		orderproduct = new OrderProduct();
		orderproductdetailDao = new OrderProductDetailDaoImpl();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json; charset=utf-8");
		
		List<OrderProductDetail> ordprodet = new ArrayList();
		
		JsonList jsonList = gson.fromJson(req.getReader(), JsonList.class);
		
		String strlast ="";
		
		String strUser_id="";
		
		Integer FinalTotal =0;	
		
		for(Redis_Data rsd:jsonList.getRedis_Data()) {
			
			strUser_id = rsd.getUser_id();
			//System.out.println(rsd.toString());
			OrderProductDetail orderproductdetail1  =new OrderProductDetail();
			//取得 user_idd
			strlast = rsd.getUser_id().substring(rsd.getUser_id().length()-1);
			//物流編號
			Integer TrkNum =Double.valueOf(Math.random()*1000000).intValue();
			FinalTotal+=rsd.getProduct_total();
			
			orderproduct.setUser_id(Integer.parseInt(strlast));
			orderproduct.setProduct_total(FinalTotal);
			orderproduct.setProduct_status(0);
			orderproduct.setTracking_number(TrkNum);
			orderproduct.setProduct_add_gc(FinalTotal/100);
			orderproduct.setProduct_deduct_gc(0);
			
			orderproductdetail1.setProductId(rsd.getProduct_id());
			orderproductdetail1.setProductQuantity(rsd.getProduct_count());
			orderproductdetail1.setProductName(rsd.getProduct_name());
			orderproductdetail1.setProductPrice(rsd.getProduct_price());
			
			ordprodet.add(orderproductdetail1);
			
			try(Jedis jedis =new Jedis()){
				
				List<String> jediss = jedis.lrange(strUser_id, 0, jedis.llen(strUser_id));
				
				for(String strs : jediss) {
					
					Redis_Data jedisPro = gson.fromJson(strs,Redis_Data.class);
					
					if(jedisPro.getProduct_id() == rsd.getProduct_id()) {
						jedis.lrem(strUser_id, 0, strs);
					}
					
				}
				
				//jedis.del(strUser_id);
				
			}
			
		}
				
		orderproductdetailDao.Inset_ProDetail(ordprodet, orderproduct);
			
		
		
		resp.getWriter().write(gson.toJson(orderproductdetailDao));
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doPost(req, resp);
	}
	
}
