package tw.idv.tibame.tha102.web.product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tw.idv.tibame.tha102.web.product.service.ProductService;
import tw.idv.tibame.tha102.web.product.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/product/update-picture")
@MultipartConfig
public class ProductUpdatePictureController extends HttpServlet {
    private ProductService productService;

    public void init() {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, IOException {
        try {
            Integer product_id = Integer.parseInt(request.getParameter("product_id"));
            Part product_picturePart = request.getPart("product_picture");
            byte[] product_picture = null;

            if (product_picturePart != null && product_picturePart.getSize() > 0) {
                try (InputStream inputStream = product_picturePart.getInputStream()) {
                    product_picture = inputStream.readAllBytes();
                }
                productService.updateProductPicture(product_id, product_picture);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"success\": true}");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        }
    }
}

