package tw.idv.tibame.tha102.web.product.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.product.service.ProductService;
import tw.idv.tibame.tha102.web.product.service.impl.ProductServiceImpl;
import tw.idv.tibame.tha102.web.product.vo.Product;

import java.io.IOException;
import java.util.List;

@WebServlet("/product/find-by-id")
public class ProductFindByIdController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;
    private static final Gson gson = new Gson();

    public ProductFindByIdController() {
        super();
        productService = new ProductServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json; charset=utf-8");

        Integer id =Integer.parseInt(request.getParameter("product_id"));

        Product productsList = productService.findById(id);
        String jsonResult = gson.toJson(productsList);
        response.getWriter().write(jsonResult);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
