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
import tw.idv.tibame.tha102.web.Redis.vo.JsonList;
import tw.idv.tibame.tha102.web.Redis.vo.Redis_Data;
import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;
@WebServlet(urlPatterns = "/OrderProdect/ordprodetControllrt")
public class ordprodetControllrt extends HttpServlet{

	private Gson gson;
	private OrderProduct orderproduct;
	private OrderProductDetail orderproductdetail1; 
	
	@Override
	public void init() throws ServletException {
		gson =new Gson();
		orderproduct =new OrderProduct();
		orderproductdetail1 =new OrderProductDetail();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8");
		
		//JSONArray  jsonarray = JSONArray.fromObject();
		List<OrderProductDetail> orderproductdetail1 = new ArrayList();
		JsonList jsonList = gson.fromJson(req.getReader(), JsonList.class);
		
		List<Redis_Data> redis_Datas =jsonList.getRedis_Data();
		
		
//	Redis_Data(user_id=user_id:1, product_id=4, product_price=null, product_count=2, product_total=400)
//	Redis_Data(user_id=user_id:1, product_id=1, product_price=null, product_count=2, product_total=2000)
		
		for(Redis_Data rrr:redis_Datas) {
			
			
			
		}
		
		
		
//		for(Redis_Data str : StringList) {
//			System.out.println(str);
//		}
//		List<Redis_Data> postsList = Arrays.asList(gson.fromJson(req.getReader(),
//				Redis_Data.class));
		
		
		//Redis_Data[] products = gson.fromJson(req.getReader(), Redis_Data[].class);
		
		//System.out.println(products);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doPost(req, resp);
	}
	
}
