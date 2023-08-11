package tw.idv.tibame.tha102.web.orderProductDetail.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;

import java.util.List;
@Repository
public interface OrderProductDetailRepo extends JpaRepository<OrderProductDetail, Integer> {
    List<OrderProductDetail> findByOrderProductId(Integer orderProductId);
}
