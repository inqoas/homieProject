package tw.idv.tibame.tha102.web.userinfo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;
@Repository
public interface OrderProductDetailRepository extends JpaRepository<OrderProductDetail, Integer>{
	List<OrderProductDetail> findByOrderProductId(Integer orderProductId);
}
