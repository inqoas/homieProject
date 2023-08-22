package tw.idv.tibame.tha102.web.Service_Collection.controller;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.Service_Collection.dao.impl.Service_CollectionDao;
import tw.idv.tibame.tha102.web.Service_Collection.dao.impl.Service_CollectionDaoImpl;
import tw.idv.tibame.tha102.web.Service_Collection.vo.Service_Collection;
import tw.idv.tibame.tha102.web.product_collection.dao.product_collectionDao;
@WebServlet(urlPatterns = "/Service_Collection/Select_ProById")
public class Select_ProById extends HttpServlet{
	
	private Gson gson;
	private Service_CollectionDao service_collectiondao;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		gson =new Gson();
		service_collectiondao =new Service_CollectionDaoImpl(); 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Service_Collection service_collection = gson.fromJson(req.getReader(), Service_Collection.class);
		
		service_collection.toString();
		//boolean str =new product_collectionDaoImpl().Select_UserId_ProId(product_collection);
			
		boolean str= service_collectiondao.Select_SerById(service_collection);
		
		if( str ) {	
			
			resp.getWriter().write("true");
			
		}else {
			
			resp.getWriter().write("false");
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
}
