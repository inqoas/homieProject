package tw.idv.tibame.tha102.web.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.product.dao.ProductDaoImpl;
@WebServlet(urlPatterns="/product/ProductFindCountController")
public class ProductFindCountController extends HttpServlet{
	
	private ProductDaoImpl productDaoImpl;
	private Gson gson =new Gson();
	
	
	@Override
	public void init() throws ServletException {
		productDaoImpl =new ProductDaoImpl();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json; charset=utf-8");
		
		List<Integer> counts =new ProductDaoImpl().getProductCount();
			
		String json =gson.toJson(counts);
		
		resp.getWriter().write(json);
		
		
	}
}
