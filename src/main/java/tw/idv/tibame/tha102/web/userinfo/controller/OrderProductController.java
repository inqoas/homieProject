package tw.idv.tibame.tha102.web.userinfo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.tha102.core.util.UserInfoJwtUtil;
import tw.idv.tibame.tha102.web.userinfo.service.OrderProductService;
import tw.idv.tibame.tha102.web.userinfo.service.impl.OrderProductUserInfoServiceImpl;
import tw.idv.tibame.tha102.web.userinfo.vo.OrderProductPill;

@RestController
@RequestMapping("/OrderProduct")
public class OrderProductController {
	@Autowired
	private UserInfoJwtUtil userInfoJwtUtil;
	@Autowired
	private OrderProductUserInfoServiceImpl orderProductUserInfoServiceImpl;

	@GetMapping("/getOrderProductPill")
	public List<OrderProductPill> getOrderProductPill(@RequestHeader("Authorization") String jwtString) {
		int userId = userInfoJwtUtil.checkUserInfoJwt(jwtString);
		OrderProductPill orderProductPill = new OrderProductPill();
		if (userId == 0) {
			orderProductPill.setMessage("驗證失敗，請重新登入");
			orderProductPill.setSuccess(false);
			List orderList = new ArrayList<OrderProductPill>();
			orderList.add(orderList);
			return orderList;
		}
		return orderProductUserInfoServiceImpl.selectAllByUserId(userId);
	}

	@GetMapping("/getOrderProductWait")
	public List<OrderProductPill> getOrderProductWait(@RequestHeader("Authorization") String jwtString) {
		int userId = userInfoJwtUtil.checkUserInfoJwt(jwtString);
		OrderProductPill orderProductPill = new OrderProductPill();
		if (userId == 0) {
			orderProductPill.setMessage("驗證失敗，請重新登入");
			orderProductPill.setSuccess(false);
			List orderList = new ArrayList<OrderProductPill>();
			orderList.add(orderList);
			return orderList;
		}
		return orderProductUserInfoServiceImpl.selectByUserIdAndProductStatus(userId, 1);
	}

	@GetMapping("/getOrderProductEnd")
	public List<OrderProductPill> getOrderProductEnd(@RequestHeader("Authorization") String jwtString) {
		int userId = userInfoJwtUtil.checkUserInfoJwt(jwtString);
		OrderProductPill orderProductPill = new OrderProductPill();
		if (userId == 0) {
			orderProductPill.setMessage("驗證失敗，請重新登入");
			orderProductPill.setSuccess(false);
			List orderList = new ArrayList<OrderProductPill>();
			orderList.add(orderList);
			return orderList;
		}
		return orderProductUserInfoServiceImpl.selectByUserIdAndProductStatus(userId, 2);
	}
	
	@GetMapping("/getOrderProductReturn")
	public List<OrderProductPill> getOrderProductReturn(@RequestHeader("Authorization") String jwtString) {
		int userId = userInfoJwtUtil.checkUserInfoJwt(jwtString);
		OrderProductPill orderProductPill = new OrderProductPill();
		if (userId == 0) {
			orderProductPill.setMessage("驗證失敗，請重新登入");
			orderProductPill.setSuccess(false);
			List orderList = new ArrayList<OrderProductPill>();
			orderList.add(orderList);
			return orderList;
		}
		return orderProductUserInfoServiceImpl.selectByUserIdAndProductStatus(userId, 3);
	}
	
	@PostMapping("/orderProductFinish")
	public boolean orderProductFinish(@RequestHeader("Authorization") String jwtString, @RequestBody Map<String, Object> requestBody) {
		int userId = userInfoJwtUtil.checkUserInfoJwt(jwtString);
		if(userId == 0) {
			return false;
		}
		int orderId = Integer.parseInt(requestBody.get("orderId").toString());
		return orderProductUserInfoServiceImpl.updateOrderProductStatus(orderId, 2);
	}
	
	@PostMapping("/orderProductCancel")
	public boolean orderProductCancel(@RequestHeader("Authorization") String jwtString, @RequestBody Map<String, Object> requestBody) {
		int userId = userInfoJwtUtil.checkUserInfoJwt(jwtString);
		if(userId == 0) {
			return false;
		}
		int orderId = Integer.parseInt(requestBody.get("orderId").toString());
		return orderProductUserInfoServiceImpl.updateOrderProductStatus(orderId, 3);
	}
}