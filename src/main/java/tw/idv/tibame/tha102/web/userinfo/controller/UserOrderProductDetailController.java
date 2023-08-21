package tw.idv.tibame.tha102.web.userinfo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.tha102.core.util.UserInfoJwtUtil;
import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;
import tw.idv.tibame.tha102.web.userinfo.service.impl.OrderProductUserInfoServiceImpl;
import tw.idv.tibame.tha102.web.userinfo.vo.OrderProductDetailDTO;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;

@RestController
@RequestMapping("/userOrderProductDetailController")
public class UserOrderProductDetailController {
	@Autowired
	private UserInfoJwtUtil userInfoJwtUtil;
	@Autowired
	private OrderProductUserInfoServiceImpl orderProductUserInfoServiceImpl;
	
	@GetMapping("/getOrderProductDetail")
	public OrderProductDetailDTO getOrderProductDetail(@RequestHeader("Authorization") String jwtString, @RequestParam("orderProductId") int orderProductId) {
	    int userId = userInfoJwtUtil.checkUserInfoJwt(jwtString);
	    if(userId == 0) {
	        return new OrderProductDetailDTO(); // or null, or handle this case accordingly
	    }

	    OrderProductDetailDTO dto = new OrderProductDetailDTO();
	    UserInfo userinfo = orderProductUserInfoServiceImpl.selectBuyerByOrderProductId(orderProductId);
	    OrderProduct orderProduct = orderProductUserInfoServiceImpl.selectOrderProductByOrderProductId(orderProductId);
	    List<OrderProductDetail> list = orderProductUserInfoServiceImpl.selectOrderProductDetailByOrderProductId(orderProductId);

	    dto.setUserinfo(userinfo);
	    dto.setOrderProduct(orderProduct);
	    dto.setOrderProductDetails(list);

	    return dto;
	}

}
