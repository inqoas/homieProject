package tw.idv.tibame.tha102.web.orderproduct.dao;

import java.util.List;

import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;

public interface OrderProductDao {
	public void insert(OrderProduct orderproduct);
	public void update(OrderProduct orderproduct);
	public List<OrderProduct> getAll();
	public List<OrderProduct> findByUserId(Integer user_id);
}
