package tw.idv.tibame.tha102.web.product.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.product.service.ProductService;
import tw.idv.tibame.tha102.web.product.service.impl.ProductServiceImpl;
import tw.idv.tibame.tha102.web.product.vo.Product;

import java.io.IOException;
import java.io.*;

import java.util.Arrays;

@WebServlet("/product/update")
public class ProductUpdateController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private ProductService productService;
    private final Gson gson = new Gson();

    public void init() {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            StringBuilder jsonBody = new StringBuilder();
            String line;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
//            Integer product_id = Integer.parseInt(request.getParameter("product_id"));
//            String product_name = request.getParameter("product_name");
//            Integer product_price = Integer.parseInt(request.getParameter("product_price"));
//            Integer product_stock = Integer.parseInt(request.getParameter("product_stock"));
//            Integer product_shipped = Integer.parseInt(request.getParameter("product_shipped"));
//            String product_introduction = request.getParameter("product_introduction");
//            Integer product_category = Integer.parseInt(request.getParameter("product_category"));
//            Integer product_review_stars = Integer.parseInt(request.getParameter("product_review_stars"));
//            Integer product_review_count = Integer.parseInt(request.getParameter("product_review_count"));
            JsonObject productRequest = gson.fromJson(jsonBody.toString(), JsonObject.class);

            Integer product_id = productRequest.get("product_id").getAsInt();
            String product_name = productRequest.get("product_name").getAsString();
            Integer product_price = productRequest.get("product_price").getAsInt();
            Integer product_stock = productRequest.get("product_stock").getAsInt();
            Integer product_shipped = productRequest.get("product_shipped").getAsInt();
            String product_introduction = productRequest.get("product_introduction").getAsString();
            Integer product_category = productRequest.get("product_category").getAsInt();
            Integer product_review_stars = productRequest.get("product_review_stars").getAsInt();
            Integer product_review_count = productRequest.get("product_review_count").getAsInt();

            Product product = productService.updateProduct(
                    product_id, product_name, product_price, product_stock, product_shipped,
                    product_introduction, product_category,
                    product_review_stars, product_review_count
            );

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"success\": true, \"product\": " + gson.toJson(product) + "}");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"success\": false, \"message\": \"" + e.getMessage() + "\", \"stackTrace\": \"" + Arrays.toString(e.getStackTrace()) + "\"}");
        }
    }
}
