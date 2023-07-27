package tw.idv.tibame.tha102.web.product.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.product.dao.ProductDaoImpl;
import tw.idv.tibame.tha102.web.product.vo.Product;
@WebServlet(urlPatterns = "/product/ProductFindItemController")
public class ProductFindItemController extends HttpServlet{

	private ProductDaoImpl productDaoImpl;
	private Gson gson = new Gson();
	
	@Override
	public void init() throws ServletException {
		productDaoImpl =new ProductDaoImpl();
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json; charset=utf-8");
		
		Product product = gson.fromJson(req.getReader(),Product.class );
		
		List<Product> products =productDaoImpl.getProduct_item(product.getProduct_category());
	
		String json = gson.toJson(products);
		
		resp.getWriter().write(json);
		
	}
}
