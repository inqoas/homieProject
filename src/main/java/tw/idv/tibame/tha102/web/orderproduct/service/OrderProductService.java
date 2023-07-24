package tw.idv.tibame.tha102.web.orderproduct.service;

import java.util.List;

import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;

public interface OrderProductService {
	public List<OrderProduct> findAll();
	public OrderProduct updateOrderProduct(OrderProduct orderProduct);
	public OrderProduct insertOrderProduct(OrderProduct orderProduct);
	public List<OrderProduct> findbyone(Integer user_id);
}
