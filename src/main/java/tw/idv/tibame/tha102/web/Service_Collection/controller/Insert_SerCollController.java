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
import tw.idv.tibame.tha102.web.product_collection.vo.Product_Collection;
@WebServlet(urlPatterns = "/Service_Collection/Insert_SerCollControllers")
public class Insert_SerCollController extends HttpServlet{

	private Gson gson;
	private Service_CollectionDao SerColl;
	
	@Override
	public void init() throws ServletException {
		gson =new Gson();
		SerColl =new Service_CollectionDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("Appliction/json; charset=utf-8");
		
		Service_Collection product_collection = gson.fromJson(req.getReader(), Service_Collection.class);
		
		//boolean str =new product_collectionDaoImpl().Select_UserId_ProId(product_collection);
			
		SerColl.Insert_Ser_Coll(product_collection);
		
		resp.getWriter().write("true");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
}
