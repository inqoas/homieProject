package tw.idv.tibame.tha102.web.orderService.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.tibame.tha102.web.orderService.repo.OrderServiceRepo;
import tw.idv.tibame.tha102.web.orderService.vo.OrderService;

import java.util.List;

@Service
public class OrderServiceService {
    private final OrderServiceRepo orderServiceRepo;

    @Autowired
    public OrderServiceService(OrderServiceRepo orderServiceRepo){
        this.orderServiceRepo = orderServiceRepo;
    }

    // 查詢所有
    public List<OrderService> getAllOrderService(){
        return orderServiceRepo.findAll();
    }

    // ID查詢
    public OrderService getOrderServiceById(Integer id){
        return orderServiceRepo.findById(id).orElse(null);
    }

    // 新增
    public OrderService createOrderService(OrderService orderService){
        return orderServiceRepo.save(orderService);
    }

    // 更新
    public OrderService updateOrderService(Integer id, OrderService updatedOrderService) {
        return orderServiceRepo.findById(id).map(existingOrderService -> {
            existingOrderService.setUserIdSeller(updatedOrderService.getUserIdSeller());
            existingOrderService.setUserIdBuyer(updatedOrderService.getUserIdBuyer());
            existingOrderService.setOrderQuantity(updatedOrderService.getOrderQuantity());
            existingOrderService.setOrderStatus(updatedOrderService.getOrderStatus());
            existingOrderService.setOrderUnitPrice(updatedOrderService.getOrderUnitPrice());
            existingOrderService.setOrderTotal(updatedOrderService.getOrderTotal());
            existingOrderService.setOrderPlacementTime(updatedOrderService.getOrderPlacementTime());
            existingOrderService.setOrderServiceDate(updatedOrderService.getOrderServiceDate());
            existingOrderService.setOrderFinishPeriod(updatedOrderService.getOrderFinishPeriod());
            existingOrderService.setOrderServiceFinishTime(updatedOrderService.getOrderServiceFinishTime());
            existingOrderService.setOrderServiceFinishDate(updatedOrderService.getOrderServiceFinishDate());
            existingOrderService.setOrderAddGc(updatedOrderService.getOrderAddGc());
            existingOrderService.setOrderDeductGc(updatedOrderService.getOrderDeductGc());
            existingOrderService.setOrderServiceItem(updatedOrderService.getOrderServiceItem());
            existingOrderService.setReviewScore(updatedOrderService.getReviewScore());
            existingOrderService.setReviewContent(updatedOrderService.getReviewContent());
            existingOrderService.setReviewTime(updatedOrderService.getReviewTime());
            existingOrderService.setRefundTime(updatedOrderService.getRefundTime());
            return orderServiceRepo.save(existingOrderService);
        }).orElseThrow(() -> new EntityNotFoundException("OrderService with id " + id + " not found."));
    }

    // 刪除
    public void deleteOrderService(Integer id){
        orderServiceRepo.deleteById(id);
    }









}
