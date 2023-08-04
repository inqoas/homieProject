package tw.idv.tibame.tha102.web.product.controller;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tw.idv.tibame.tha102.web.product.dao.ProductDaoImpl;
import tw.idv.tibame.tha102.web.product.vo.Product;
@WebServlet(urlPatterns="/Product/ProductDetail")
public class ProductDetail extends HttpServlet{
	
	private ProductDaoImpl productDaoImpl;
	private Product product;
	Gson gson;
	
	
	@Override
	public void init() throws ServletException {
		productDaoImpl = new ProductDaoImpl();
		product =new Product();
		gson = new Gson();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		
		resp.setContentType("application/json; charset=utf-8");
		
		//HttpSession session =req.getSession();
		
		Integer id =Integer.parseInt(req.getParameter("product_id"));
		
		product = productDaoImpl.getById(id);
		
		req.setAttribute("product", product);
		
		req.getRequestDispatcher("/WEB-INF/jsp/product-left-thumbnail.jsp").forward(req, resp);
		
		//resp.getWriter().write( gson.toJson(product));
		
	}
	
}
