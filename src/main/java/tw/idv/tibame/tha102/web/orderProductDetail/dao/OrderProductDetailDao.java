package tw.idv.tibame.tha102.web.orderProductDetail.dao;

import java.util.List;

import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;

public interface OrderProductDetailDao {
	
	void Inset_ProDetail(List<OrderProductDetail> orderproductdetails,OrderProduct orderproduct);
}
