package tw.idv.tibame.tha102.web.userinfo.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.core.util.UserInfoJwtUtil;
import tw.idv.tibame.tha102.web.userinfo.service.CollectionService;
import tw.idv.tibame.tha102.web.userinfo.vo.Product;
import tw.idv.tibame.tha102.web.userinfo.vo.ProductCollection;
import tw.idv.tibame.tha102.web.userinfo.vo.Service;
import tw.idv.tibame.tha102.web.userinfo.vo.ServiceCollection;

@RestController
@RequestMapping("/collection")
public class CollectionController {
	@Autowired
	private UserInfoJwtUtil userInfoJwtUtil;
	@Autowired
	private CollectionService collectionService;

	@GetMapping("/getAllProductCollection")
	public List<Product> getAllProductCollection(@RequestHeader("Authorization") String authorization) {
		int userId = userInfoJwtUtil.checkUserInfoJwt(authorization);
		if (userId < 1) {
			return Collections.emptyList();
		}
		return collectionService.getProductCollection(userId);
	}

	@GetMapping("/getAllServiceCollection")
	public List<Service> getAllServiceCollection(@RequestHeader("Authorization") String authorization) {
		int userId = userInfoJwtUtil.checkUserInfoJwt(authorization);
		if (userId < 1) {
			return Collections.emptyList();
		}
		return collectionService.getServiceCollection(userId);
	}
	
	@GetMapping("/getProductPic")
	public void getProductPic(@RequestParam("productId") int productId, HttpServletResponse response) {
		byte[] pic = collectionService.getProductCollectionPic(productId);
		response.setContentType("image/jpeg");
		if(pic != null) {
			try {
				response.getOutputStream().write(pic);
	            response.getOutputStream().flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			byte[] whitePic = collectionService.getProductCollectionPic(0);
			try {
				response.getOutputStream().write(whitePic);
				response.getOutputStream().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@GetMapping("/getServicePic")
	public void getServicePic(@RequestParam("serviceId") int serviceId, HttpServletResponse response) {
		byte[] pic = collectionService.getServiceCollectionPic(serviceId);
		if(pic != null) {
			response.setContentType("image/jpeg");
			try {
				response.getOutputStream().write(pic);
				response.getOutputStream().flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			byte[] whitePic = collectionService.getProductCollectionPic(0);
			try {
				response.getOutputStream().write(whitePic);
				response.getOutputStream().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@DeleteMapping("/deleteProductCollection")
	public ProductCollection deleteProductCollection(@RequestBody Map<String, String> reqJson, @RequestHeader("Authorization") String authorization) {
		String productName = reqJson.get("collectionName");
		int userId= userInfoJwtUtil.checkUserInfoJwt(authorization);
		if(userId == 0) {
			ProductCollection productCollection = new ProductCollection();
			productCollection.setSuccess(false);
			productCollection.setMessage("驗證失敗，請重新登入");
			return productCollection;
		}
		
		return collectionService.deleteProductCollection(productName, userId);
	} 
	
	@DeleteMapping("/deleteServiceCollection")
	public ServiceCollection deleteServiceCollection(@RequestBody Map<String, String> reqJson, @RequestHeader("Authorization") String authorization) {
		String serviceName = reqJson.get("collectionName");
		int userId= userInfoJwtUtil.checkUserInfoJwt(authorization);
		if(userId == 0) {
			ServiceCollection serviceCollection = new ServiceCollection();
			serviceCollection.setSuccess(false);
			serviceCollection.setMessage("驗證失敗，請重新登入");
			return serviceCollection;
		}
		
		return collectionService.deleteServiceCollection(serviceName, userId);
	} 
}
