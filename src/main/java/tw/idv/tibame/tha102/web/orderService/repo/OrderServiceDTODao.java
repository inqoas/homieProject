package tw.idv.tibame.tha102.web.orderService.repo;

import jakarta.persistence.Query;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import tw.idv.tibame.tha102.web.orderService.vo.OrderService;
import tw.idv.tibame.tha102.web.orderService.vo.OrderServiceDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderServiceDTODao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<OrderServiceDTO> findOrderServicesWithServiceName(Integer orderServiceId) {
        String sql = "SELECT os.*, s.service_name FROM order_service os " +
                "JOIN service s ON os.service_id = s.service_id WHERE os.order_service_id =?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, orderServiceId);
        List<Object[]> resultList = query.getResultList();

        List<OrderServiceDTO> orderServicesDTOs = new ArrayList<>();
        for (Object[] row : resultList) {
            OrderService orderService = new OrderService();
            orderService.setOrderServiceId((Integer) row[0]);
            orderService.setUserIdSeller((Integer) row[1]);
            orderService.setUserIdBuyer((Integer) row[2]);
            orderService.setOrderQuantity((Integer) row[3]);
            orderService.setOrderStatus((Integer) row[4]);
            orderService.setOrderUnitPrice((Integer) row[5]);
            orderService.setOrderTotal((Integer) row[6]);
            orderService.setOrderPlacementTime((Timestamp) row[7]);
            orderService.setOrderServiceDate((Timestamp) row[8]);
            orderService.setOrderFinishPeriod((Integer) row[9]);
            orderService.setOrderServiceFinishTime((Timestamp) row[10]);
            orderService.setOrderServiceFinishDate((Timestamp) row[11]);
            orderService.setOrderAddGc((Integer) row[12]);
            orderService.setOrderDeductGc((Integer) row[13]);
            orderService.setOrderServiceItem((Integer) row[14]);
            orderService.setReviewScore((Integer) row[15]);
            orderService.setReviewContent((String) row[16]);
            orderService.setReviewTime((Timestamp) row[17]);
            orderService.setRefundTime((Timestamp) row[18]);
            orderService.setServiceId((Integer) row[19]);

            String serviceName = (String) row[row.length - 1];

            // 創建DTO並設置屬性
            OrderServiceDTO orderServiceDTO = new OrderServiceDTO();
            orderServiceDTO.setOrderService(orderService);
            orderServiceDTO.setServiceName(serviceName);

            // 將新創建的DTO對象添加到結果列表中
            orderServicesDTOs.add(orderServiceDTO);
        }
        return orderServicesDTOs;
    }

}
