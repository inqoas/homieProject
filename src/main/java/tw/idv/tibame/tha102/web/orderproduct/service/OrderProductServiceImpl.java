package tw.idv.tibame.tha102.web.orderproduct.service;

import java.util.List;

import org.hibernate.SessionFactory;

import tw.idv.tibame.tha102.web.orderproduct.dao.OrderProductDao;
import tw.idv.tibame.tha102.web.orderproduct.dao.OrderProductDaoImpl;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;

public class OrderProductServiceImpl implements OrderProductService{
	
	private OrderProductDao dao;
	
	public OrderProductServiceImpl(SessionFactory sessionFactory) {
		dao = new OrderProductDaoImpl(sessionFactory);
		
	}

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
	public List<OrderProduct> findbyone(Integer user_id) {
		return dao.findByUserId(user_id);
	}

}