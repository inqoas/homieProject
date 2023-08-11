package tw.idv.tibame.tha102.web.product.service.impl;

import java.util.List;

import tw.idv.tibame.tha102.web.product.dao.ProductDao;
import tw.idv.tibame.tha102.web.product.dao.ProductDaoImpl;
import tw.idv.tibame.tha102.web.product.service.ProductService;
import tw.idv.tibame.tha102.web.product.vo.Product;



public class ProductServiceImpl implements ProductService {
	
	private final ProductDao dao;

	public ProductServiceImpl() {
		dao = new ProductDaoImpl();
	}
	
	@Override
	public List<Product> findAll() {
		return dao.getAll();
	}

	@Override
	public void deleteProduct(Integer product_id) {
		dao.delete(product_id);
	}

	@Override
	public Product findById(Integer product_id) {
		return dao.getById(product_id);
	}

	@Override
	public Product updateProduct(Integer product_id, String product_name, Integer product_price, Integer product_stock,
			Integer product_shipped, String product_introduction, Integer product_category,
			Integer product_review_stars, Integer product_review_count) {
		
		Product product = dao.getById(product_id);
		
		product.setProduct_id(product_id);
		product.setProduct_name(product_name);
		product.setProduct_price(product_price);
		product.setProduct_stock(product_stock);
		product.setProduct_shipped(product_shipped);
		product.setProduct_introduction(product_introduction);
		product.setProduct_category(product_category);
		product.setProduct_review_stars(product_review_stars);
		product.setProduct_review_count(product_review_count);
		dao.update(product);
		return product;
	}

	@Override
	public Product newProduct(String product_name, Integer product_price, Integer product_stock,
			Integer product_shipped, String product_introduction, byte[] product_picture, Integer product_category,
			Integer product_review_stars, Integer product_review_count) {
		
		Product product = new Product();
		
		product.setProduct_name(product_name);
		product.setProduct_price(product_price);
		product.setProduct_stock(product_stock);
		product.setProduct_shipped(product_shipped);
		product.setProduct_introduction(product_introduction);
		product.setProduct_picture(product_picture);
		product.setProduct_category(product_category);
		product.setProduct_review_stars(product_review_stars);
		product.setProduct_review_count(product_review_count);
		dao.insert(product);
		return product;
	}

	@Override
	public void updateProductPicture(Integer productId, byte[] productPicture) {
		// 這裡可以添加任何業務邏輯檢查

		dao.updateProductPicture(productId, productPicture);
	}
	

}
