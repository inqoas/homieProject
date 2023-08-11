package tw.idv.tibame.tha102.web.orderService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.tibame.tha102.web.orderService.vo.OrderService;

@Repository
public interface OrderServiceRepo extends JpaRepository<OrderService, Integer> {
}
