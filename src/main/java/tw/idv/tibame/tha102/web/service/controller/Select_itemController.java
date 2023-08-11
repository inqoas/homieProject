package tw.idv.tibame.tha102.web.service.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.service.dao.ServiceDao;
import tw.idv.tibame.tha102.web.service.dao.Imp.ServiceDaoImpl;
import tw.idv.tibame.tha102.web.service.vo.Service;
@WebServlet(urlPatterns = "/service/Select_itemController")
public class Select_itemController extends HttpServlet{
	
	private static ServiceDao servicedao ;
	private static Gson gson;
	
	@Override
	public void init() throws ServletException {
		servicedao =new ServiceDaoImpl();
		gson =new Gson();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("Application/json; charset=utf-8");
		
		//{"area_name":"01","service_id_code":"01","date":"","morning":"0"}: 
		
		JsonElement jsonElement =new JsonParser().parseReader(req.getReader());
		   
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		
		JsonElement area_name = jsonObject.get("area_name");
		//System.out.println("Name: " +area_name.getAsString()); 
		
		JsonElement service_id_code = jsonObject.get("service_id_code");
		//System.out.println("service_id_code: " +service_id_code.getAsInt()); 
		
		JsonElement date = jsonObject.get("date");
		//System.out.println("date: " +date.getAsString()); 
		
		JsonElement morning = jsonObject.get("morning");
		//System.out.println("morning: " +morning.getAsInt()); 		
		
		List<Service> ll = new ServiceDaoImpl().select_check(area_name.getAsString(), service_id_code.getAsInt(), date.getAsString(), morning.getAsInt());		  
		
		resp.getWriter().write(gson.toJson(ll));
		
		
//		for(Service services : ll) {			
//			System.out.println( services );			     
//		}

		
//		System.out.println( service.getAvailabletimes().getDate() );
//		System.out.println( service.getAreas().getArea_name());
//		System.out.println( service.getService_id_code());
//		System.out.println( service.getAvailabletimes().getMorning());
		
				
		
	}
}
