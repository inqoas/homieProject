package tw.idv.tibame.tha102.web.orderproduct.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.tibame.tha102.web.orderproduct.dao.OrderProductDao;
import tw.idv.tibame.tha102.web.orderproduct.service.OrderProductService;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;

@Service
public class OrderProductServiceImpl implements OrderProductService {
	
	@Autowired
	private OrderProductDao dao;
	
	@Override
	public List<OrderProduct> findAll() {
		return dao.getAll();
	}

	@Override
	public OrderProduct updateOrderProduct(OrderProduct orderProduct) {
		dao.update(orderProduct);
		return orderProduct;
	}

	@Override
	public OrderProduct insertOrderProduct(OrderProduct orderProduct) {
		dao.insert(orderProduct);
		return orderProduct;
	}

	@Override
	public List<OrderProduct> getByUserId(Integer user_id) {
		return dao.findByUserId(user_id);
	}

	@Override
	public OrderProduct getByOrderProductId(Integer order_product_id) {
		OrderProduct orderProduct = dao.findByOrderProductId(order_product_id);

		if(orderProduct == null){
			throw new RuntimeException("OrderProduct not found for the provided id");
		}

		return orderProduct;
	}

}
