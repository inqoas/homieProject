package tw.idv.tibame.tha102.web.product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.product.service.ProductService;
import tw.idv.tibame.tha102.web.product.service.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet("/product/delete-by-id")
public class ProductDeleteByIdController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;

    public void init() {
        productService = new ProductServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer productId = Integer.valueOf(request.getParameter("product_id"));
            productService.deleteProduct(productId);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"success\":true}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\":false, \"message\":\"" + e.getMessage() + "\"}");
        }
    }
}
