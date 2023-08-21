package tw.idv.tibame.tha102.web.product_collection.dao;

import tw.idv.tibame.tha102.web.product_collection.vo.Product_Collection;

public interface product_collectionDao {

	void Insert_product_collectionDao(Product_Collection product_collection );
	
	boolean Select_UserId_ProId(Product_Collection product_collection);
	
	void delete_UserId_ProId(Product_Collection product_collection);
}
