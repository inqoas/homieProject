package tw.idv.tibame.tha102.web.userinfo.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;
import tw.idv.tibame.tha102.web.userinfo.dao.MemberDao;
import tw.idv.tibame.tha102.web.userinfo.dao.OrderProductDetailRepository;
import tw.idv.tibame.tha102.web.userinfo.dao.OrderProductRepository;
import tw.idv.tibame.tha102.web.userinfo.service.OrderProductService;
import tw.idv.tibame.tha102.web.userinfo.vo.OrderProductPill;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;
@Service
public class OrderProductUserInfoServiceImpl implements OrderProductService{
	@Autowired
	private OrderProductRepository orderProductRepository;
	@Autowired
	private OrderProductDetailRepository orderProductDetailRepository;
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public List<OrderProductPill> selectAllByUserId(int userId) {
	    List<Object> obList = orderProductRepository.selectAllOrderProductAndDetailByUserId(userId);
	    List<OrderProductPill> orderProductPills = new ArrayList<>();

	    for (Object result : obList) {
	        Object[] row = (Object[]) result;

	        
	        Integer productId = (Integer) row[0];
	        Date deliveryTime = (Date) row[1];
	        Integer productStatus = (Integer) row[2];
	        String productName = (String) row[3];
	        Integer productQuantity = (Integer) row[4];
	        Integer productPrice = (Integer) row[5];
	 
	        
	        OrderProductPill orderProductPill = new OrderProductPill();
	        orderProductPill.setProductId(productId);
	        orderProductPill.setProductName(productName);
	        orderProductPill.setProductQuantity(productQuantity);
	        orderProductPill.setProductPrice(productPrice * productQuantity);
	        orderProductPill.setProductPlacementTime(deliveryTime);
	        orderProductPill.setProductStatus(productStatus);
	        orderProductPills.add(orderProductPill);
	    }

	    return orderProductPills;
	}

	public List<OrderProductPill> selectByUserIdAndProductStatus(int userId,int Status) {
	    List<Object> obList = orderProductRepository.selectAllOrderProductAndDetailByUserIdAndProductStatus(userId, Status);
	    List<OrderProductPill> orderProductPills = new ArrayList<>();

	    for (Object result : obList) {
	        Object[] row = (Object[]) result;

	        
	        Integer productId = (Integer) row[0];
	        Date deliveryTime = (Date) row[1];
	        Integer productStatus = (Integer) row[2];
	        String productName = (String) row[3];
	        Integer productQuantity = (Integer) row[4];
	        Integer productPrice = (Integer) row[5];
	 
	        
	        OrderProductPill orderProductPill = new OrderProductPill();
	        orderProductPill.setProductId(productId);
	        orderProductPill.setProductName(productName);
	        orderProductPill.setProductQuantity(productQuantity);
	        orderProductPill.setProductPrice(productPrice * productQuantity);
	        orderProductPill.setProductPlacementTime(deliveryTime);
	        orderProductPill.setProductStatus(productStatus);
	        orderProductPills.add(orderProductPill);
	    }

	    return orderProductPills;
	}

	@Override
	public boolean updateOrderProductStatus(int OrderProductId, int orderProductStatus) {
		OrderProduct orderProduct = orderProductRepository.getById(OrderProductId);
		if(orderProduct == null) {
			return false;
		}
		orderProduct.setProduct_status(orderProductStatus);
		orderProduct.setProduct_finish_date(new Timestamp(System.currentTimeMillis()));
		try {
			orderProductRepository.save(orderProduct);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public UserInfo selectBuyerByOrderProductId(int orderProductId) {
		int userId = orderProductRepository.getById(orderProductId).getUser_id();
		return memberDao.selectByUserId(userId);
	}

	@Override
	public OrderProduct selectOrderProductByOrderProductId(int OrderProductId) {	
		return orderProductRepository.getById(OrderProductId);
	}

	@Override
	public List<OrderProductDetail> selectOrderProductDetailByOrderProductId(int OrderProductId) {
		return orderProductDetailRepository.findByOrderProductId(OrderProductId);
	}
}
