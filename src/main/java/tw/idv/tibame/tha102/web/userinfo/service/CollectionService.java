package tw.idv.tibame.tha102.web.userinfo.service;

import java.util.List;


import tw.idv.tibame.tha102.web.userinfo.vo.Product;
import tw.idv.tibame.tha102.web.userinfo.vo.ProductCollection;
import tw.idv.tibame.tha102.web.userinfo.vo.Service;
import tw.idv.tibame.tha102.web.userinfo.vo.ServiceCollection;

public interface CollectionService {
	//新增ProductCollection
	ProductCollection insertProductCollection(ProductCollection productCollection);
	//新增ServiceCollection
	ServiceCollection insertServiceCollection(ServiceCollection serviceCollection);
	//刪除ProductCollection
	ProductCollection deleteProductCollection(String productName, int userId);
	//刪除ServiceCollection
	ServiceCollection deleteServiceCollection(String serciveName, int userId);
	//取得除圖片外的ProductCollection資訊
	List<Product> getProductCollection(int userId);
	//取得除圖片外的ServiceCollection資訊
	List<Service> getServiceCollection(int userId);
	//取得ProductCollection的圖片
	byte[] getProductCollectionPic(int productId);
	//取得ServiceCollection的圖片
	byte[] getServiceCollectionPic(int serviceCollectionId);
}
