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
@WebServlet(urlPatterns = "/Service_Collection/delete_SerCollController")
public class delete_SerCollController extends HttpServlet{
	
	private Gson gson;
	private Service_CollectionDao SerColl;
	
	@Override
	public void init() throws ServletException {
		gson =new Gson();
		
		SerColl=new Service_CollectionDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("Appliction/json; charset=utf-8");
		
		Service_Collection product_collection = gson.fromJson(req.getReader(), Service_Collection.class);
		
		//boolean str =new product_collectionDaoImpl().Select_UserId_ProId(product_collection);
			
		SerColl.Delete_SerById(product_collection);
		
		resp.getWriter().write("true");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
}
