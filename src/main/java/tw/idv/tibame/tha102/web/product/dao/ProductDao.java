package tw.idv.tibame.tha102.web.product.dao;

import java.util.List;

import org.apache.el.parser.AstInteger;

import tw.idv.tibame.tha102.web.product.vo.Product;

public interface ProductDao {
	public void insert(Product product);
	
	public void update(Product product);

	public void delete(Integer product_id);

	public List<Product> getAll();
}
