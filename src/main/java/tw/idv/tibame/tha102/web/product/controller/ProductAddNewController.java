package tw.idv.tibame.tha102.web.product.controller;

import java.io.*;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tw.idv.tibame.tha102.web.product.service.ProductService;
import tw.idv.tibame.tha102.web.product.service.ProductServiceImpl;

@MultipartConfig(maxFileSize = 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
@WebServlet("/product/add-new")
public class ProductAddNewController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private final ProductService productService;
    
    public ProductAddNewController() {
        super();
        productService = new ProductServiceImpl();
    }
    
    public byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    public static class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setContentType("application/json; charset=utf-8");
            
            String product_name = request.getParameter("product_name");
            Integer product_category = Integer.parseInt(request.getParameter("product_category"));
            String product_introduction = request.getParameter("product_introduction");
            Integer product_price = Integer.parseInt(request.getParameter("product_price"));
            Integer product_stock = Integer.parseInt(request.getParameter("product_stock"));
    
            Integer product_shipped = 0;
            Integer product_review_stars = 0;
            Integer product_review_count = 0;
    
            // 获取上传的照片
            Part filePart = request.getPart("product_picture");
            InputStream fileInputStream = filePart.getInputStream();
            byte[] product_picture = readInputStream(fileInputStream);
    
            // 将商品信息和照片保存到数据库
            productService.newProduct(product_name, product_price, product_stock, product_shipped, product_introduction, product_picture, product_category, product_review_stars, product_review_count);
    
            // 返回响应
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            ResponseMessage responseMessage = new ResponseMessage("新增商品成功");
            out.print(new Gson().toJson(responseMessage));
            out.flush();
        } catch (Exception e) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            ResponseMessage responseMessage = new ResponseMessage("新增商品失敗，原因：" + e.getMessage());
            out.print(new Gson().toJson(responseMessage));
            out.flush();
        }
    }

}
