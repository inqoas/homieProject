package tw.idv.tibame.tha102.web.userinfo.dao;

import java.util.List;

import tw.idv.tibame.tha102.web.userinfo.vo.Product;
import tw.idv.tibame.tha102.web.userinfo.vo.ProductCollection;

public interface ProductCollectionDao {
	public int insert(ProductCollection productCollection);
	public int delete(String productName, int userId);
	public ProductCollection selectProductCollectionByUserIdAndProductId(int userId, int productId);
	public List<Product> selectAllbyUserId(int userId);
	public byte[] selectPicByproductCollectId(int productId);
}
