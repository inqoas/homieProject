package tw.idv.tibame.tha102.web.orderService.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import tw.idv.tibame.tha102.web.orderService.dao.impl.OrderSerDao;
import tw.idv.tibame.tha102.web.orderService.dao.impl.OrderSerDaoImpl;
import tw.idv.tibame.tha102.web.orderService.vo.OrderService;
import tw.idv.tibame.tha102.web.orderproduct.dao.OrderProductDao;
import tw.idv.tibame.tha102.web.orderproduct.dao.OrderProductDaoImpl;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;
import tw.idv.tibame.tha102.web.product.vo.Product;
@WebServlet(urlPatterns = "/OrderService/InsertIntoOrdSerControllrt")
public class InsertIntoOrdDetControllrt extends HttpServlet{

	private Gson gson;
	private OrderSerDao orderserdaoimpl;
	
	@Override
	public void init() throws ServletException {
		gson =new Gson();
		orderserdaoimpl =new OrderSerDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		resp.setContentType("application/json; charset=utf-8");
		
		List<OrderService> ordsres = new ArrayList();
		
		JsonList jsonList = gson.fromJson(req.getReader(), JsonList.class);
		
		String strlastId ="";
		
		String strUser_id="";
		
		Integer FinalTotal =0;	
		
		for(Redis_Data_Service rsd:jsonList.getRedis_Data_Service()) {
			
			System.out.println(rsd.toString());
			
			strUser_id = rsd.getUser_id();
			//System.out.println(rsd.toString());
			OrderService orderservice  =new OrderService();
			//取得 user_idd
			strlastId = rsd.getUser_id().substring(rsd.getUser_id().length()-1);
			//物流編號
		    //Integer TrkNum =Double.valueOf(Math.random()*1000000).intValue();
			FinalTotal+=rsd.getService_total();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			System.out.println(rsd.getService_Date());
			Timestamp timestamp =null;
			try {
			    java.util.Date date = sdf.parse(rsd.getService_Date());
			    timestamp = new Timestamp(date.getTime());
			  
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			
			orderservice.setUserIdBuyer(Integer.parseInt(strlastId));
			orderservice.setUserIdSeller(rsd.getSeller_id());
			orderservice.setOrderQuantity(rsd.getService_count());
			orderservice.setOrderStatus(0);
			orderservice.setOrderPlacementTime(timestamp);
			orderservice.setOrderUnitPrice(rsd.getService_total());
		    orderservice.setOrderTotal(FinalTotal);
		    orderservice.setOrderServiceDate(timestamp);
			orderservice.setOrderFinishPeriod(0);
			orderservice.setOrderAddGc(FinalTotal/100);
			orderservice.setOrderServiceItem(rsd.getService_id_code());
			orderservice.setServiceId(rsd.getService_id());
			
			ordsres.add(orderservice);
			
			
			try(Jedis jedis =new Jedis()){
				
				StringBuilder sbd = new StringBuilder(strUser_id).append("_Service");
				
				List<String> jediss = jedis.lrange(sbd.toString(), 0, jedis.llen(sbd.toString()));
				
				for(String strs : jediss) {
					
					Redis_Data_Service jedisPro = gson.fromJson(strs,Redis_Data_Service.class);
					
					if(jedisPro.getService_id() == rsd.getService_id()) {
						jedis.lrem(sbd.toString(), 0, strs);
					}
					
				}

			}
				
			orderserdaoimpl.Insert_OrdSer(ordsres);
				
		}
		
		resp.getWriter().write(gson.toJson(ordsres));
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doPost(req, resp);
	}
	
}
