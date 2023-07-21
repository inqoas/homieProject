package tw.idv.tibame.tha102.web.product.service;

import java.util.List;

import tw.idv.tibame.tha102.web.product.vo.Product;


public interface ProductService {
	public List<Product> findAll();
	public Product updateProduct(Integer product_id, String product_name, Integer product_price, Integer product_stock, Integer product_shipped, String product_introduction, byte[] product_picture, Integer product_category, Integer product_review_stars, Integer product_review_count);
	public Product newProduct(String product_name, Integer product_price, Integer product_stock, Integer product_shipped, String product_introduction, byte[] product_picture, Integer product_category, Integer product_review_stars, Integer product_review_count);
	public void deleteProduct(Integer product_id);
}
