package tw.idv.tibame.tha102.web.product_collection.controller;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.product_collection.dao.product_collectionDao;
import tw.idv.tibame.tha102.web.product_collection.dao.Impl.product_collectionDaoImpl;
import tw.idv.tibame.tha102.web.product_collection.vo.Product_Collection;
@WebServlet(urlPatterns = "/product_collection/Select_ProCollController")
public class Select_ProCollController extends HttpServlet{
	
	private Gson gson;
	private product_collectionDao product_collectionDao;
	
	@Override
	public void init() throws ServletException {
		gson =new Gson();
		product_collectionDao = new product_collectionDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("Appliction/json; charset=utf-8");
		
		Product_Collection product_collection = gson.fromJson(req.getReader(), Product_Collection.class);
		
		boolean str =new product_collectionDaoImpl().Select_UserId_ProId(product_collection);
		
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
