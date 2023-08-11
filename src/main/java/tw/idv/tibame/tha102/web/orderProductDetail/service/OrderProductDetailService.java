package tw.idv.tibame.tha102.web.orderProductDetail.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.tibame.tha102.web.orderProductDetail.repo.OrderProductDetailRepo;
import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;

import java.util.List;

@Service
public class OrderProductDetailService {
    private  final OrderProductDetailRepo orderProductDetailRepo;
    @Autowired
    public OrderProductDetailService(OrderProductDetailRepo orderProductDetailRepo) {
        this.orderProductDetailRepo = orderProductDetailRepo;
    }

    // 查詢所有
    public List<OrderProductDetail> getAllOrderProductDetails() {
        return orderProductDetailRepo.findAll();
    }

    // ID查詢
    public OrderProductDetail getOrderProductDetailById(Integer id) {
        return orderProductDetailRepo.findById(id).orElse(null);
    }

    // 新增
    public OrderProductDetail createOrderProductDetail(OrderProductDetail orderProductDetail) {
        return orderProductDetailRepo.save(orderProductDetail);
    }

    // 更新
    public OrderProductDetail updateOrderProductDetail(Integer id, OrderProductDetail updatedOrderProductDetail) {
        return orderProductDetailRepo.findById(id).map(existingOrderProductDetail -> {
            existingOrderProductDetail.setOrderProductId(updatedOrderProductDetail.getOrderProductId());
            existingOrderProductDetail.setProductId(updatedOrderProductDetail.getProductId());
            existingOrderProductDetail.setProductQuantity(updatedOrderProductDetail.getProductQuantity());
            existingOrderProductDetail.setProductName(updatedOrderProductDetail.getProductName());
            existingOrderProductDetail.setProductPrice(updatedOrderProductDetail.getProductPrice());
            existingOrderProductDetail.setProductScore(updatedOrderProductDetail.getProductScore());
            existingOrderProductDetail.setProductContent(updatedOrderProductDetail.getProductContent());
            return orderProductDetailRepo.save(existingOrderProductDetail);
        }).orElseThrow(() -> new EntityNotFoundException("OrderProductDetail with id " + id + " not found."));
    }

    // 刪除
    public void deleteOrderProductDetail(Integer id) {
        orderProductDetailRepo.deleteById(id);
    }

    public List<OrderProductDetail> getOrderProductDetailsByOrderProductId(Integer orderProductId) {
        return orderProductDetailRepo.findByOrderProductId(orderProductId);
    }
}
