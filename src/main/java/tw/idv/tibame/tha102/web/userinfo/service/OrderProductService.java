package tw.idv.tibame.tha102.web.userinfo.service;

import java.util.List;

import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;
import tw.idv.tibame.tha102.web.userinfo.vo.OrderProductPill;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;

public interface OrderProductService {
	public List<OrderProductPill> selectAllByUserId(int userId);
	public List<OrderProductPill> selectByUserIdAndProductStatus(int userId, int Status);
	public boolean updateOrderProductStatus(int OrderProductId, int orderProductStatus);
	public UserInfo selectBuyerByOrderProductId(int OrderProductId);
	public OrderProduct selectOrderProductByOrderProductId(int OrderProductId);
	public List<OrderProductDetail> selectOrderProductDetailByOrderProductId(int OrderProductId);
}
