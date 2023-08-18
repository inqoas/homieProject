package tw.idv.tibame.tha102.web.product.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.product.dao.ProductDaoImpl;
import tw.idv.tibame.tha102.web.product.vo.Product;
@WebServlet(urlPatterns = "/product/ProductFindImgController")
public class ProductFindImgController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ProductDaoImpl productDaoImpl; 
	
	
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
		
		resp.setContentType("image/jpg");
		
		Integer id =Integer.parseInt(req.getParameter("product_id"));
		
		Product product =productDaoImpl.getProduct_ImgById(id);
		
		resp.getOutputStream().write(product.getProduct_picture());
		
		resp.getOutputStream().flush();
		
		
//		InputStream input =new ByteArrayInputStream(product.getProduct_picture());
//		
//		BufferedInputStream in = new BufferedInputStream(input);
//		int num = input.available();
//				byte[] buf = new byte[num]; // 4K buffer
//				int len;
//				while ((len = in.read(buf)) != -1) {
//					out.write(buf, 0, len);
//				}
				
//		in.close();
//		input.close();
//		out.close();
		
	}

}
