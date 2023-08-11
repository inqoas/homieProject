package tw.idv.tibame.tha102.web.userinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import tw.idv.tibame.tha102.web.userinfo.dao.ProductCollectionDao;
import tw.idv.tibame.tha102.web.userinfo.dao.ServiceCollectionDao;
import tw.idv.tibame.tha102.web.userinfo.service.CollectionService;
import tw.idv.tibame.tha102.web.userinfo.vo.Product;
import tw.idv.tibame.tha102.web.userinfo.vo.ProductCollection;
import tw.idv.tibame.tha102.web.userinfo.vo.Service;
import tw.idv.tibame.tha102.web.userinfo.vo.ServiceCollection;
@org.springframework.stereotype.Service

public class CollectionServiceImpl implements CollectionService {
	@Autowired
	private ProductCollectionDao productCollectionDao;
	@Autowired
	private ServiceCollectionDao serviceCollectionDao;
	
	@Override
	@Transactional
	public ProductCollection insertProductCollection(ProductCollection productCollection) {
		ProductCollection checkCollection =  productCollectionDao.selectProductCollectionByUserIdAndProductId(productCollection.getUserId(), productCollection.getProductId());
		if(checkCollection != null) {
			productCollection.setSuccess(false);
			productCollection.setMessage("新增重複的收藏");
			return productCollection;
		}
		int resultCount = productCollectionDao.insert(productCollection);
		if(resultCount < 1) {
			productCollection.setSuccess(false);
			productCollection.setMessage("新增失敗，請聯絡客服");
			return productCollection;
		}
		productCollection.setSuccess(true);
		return productCollection;
	}

	@Override
	@Transactional
	public ServiceCollection insertServiceCollection(ServiceCollection serviceCollection) {
		ServiceCollection checkServiceCollection = serviceCollectionDao.selectServiceCollectionByUserIdAndServiceId(serviceCollection.getUserId(), serviceCollection.getServiceId());
		if(checkServiceCollection != null) {
			serviceCollection.setSuccess(false);
			serviceCollection.setMessage("新增失敗，請聯絡客服");
			return serviceCollection;
		}
		int resultCount = serviceCollectionDao.insert(serviceCollection);
		if(resultCount < 1) {
			serviceCollection.setSuccess(false);
			serviceCollection.setMessage("新增失敗，請聯絡客服");
			return serviceCollection;
		}
		serviceCollection.setSuccess(true);
		return serviceCollection;
	}

	@Override
	@Transactional
	public ProductCollection deleteProductCollection(String productName, int userId) {
		int resultCount = productCollectionDao.delete(productName, userId);
		ProductCollection productCollection = new ProductCollection();
		if(resultCount < 1) {
			productCollection.setSuccess(false);
			productCollection.setMessage("刪除失敗，請聯絡客服");
			return productCollection;
		}
		productCollection.setSuccess(true);
		return productCollection;
	}

	@Override
	@Transactional
	public ServiceCollection deleteServiceCollection(String serciveName, int userId) {
		int resultCount = serviceCollectionDao.delete(serciveName, userId);
		ServiceCollection serviceCollection = new ServiceCollection();
		if(resultCount < 1) {
			serviceCollection.setSuccess(false);
			serviceCollection.setMessage("刪除失敗，請聯絡客服");
			return serviceCollection;
		}
		serviceCollection.setSuccess(true);
		return serviceCollection;
	}

	@Override
	public List<Product> getProductCollection(int userId) {
		List<Product> productList = productCollectionDao.selectAllbyUserId(userId);
		return productList;
	}

	@Override
	public List<Service> getServiceCollection(int userId) {
		List<Service> serviceList = serviceCollectionDao.selectAllByUserId(userId);
		return serviceList;
	}

	@Override
	public byte[] getProductCollectionPic(int productId) {
		byte[] pic = productCollectionDao.selectPicByproductCollectId(productId);
		return pic;
	}

	@Override
	public byte[] getServiceCollectionPic(int serviceId) {
		byte[] pic = serviceCollectionDao.selectPicByServiceId(serviceId);
		return pic;
	}

}
