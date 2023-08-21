package tw.idv.tibame.tha102.web.orderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.idv.tibame.tha102.web.orderService.repo.OrderServiceDTODao;
import tw.idv.tibame.tha102.web.orderService.service.OrderServiceService;
import tw.idv.tibame.tha102.web.orderService.vo.OrderService;
import tw.idv.tibame.tha102.web.orderService.vo.OrderServiceDTO;

import java.util.List;

@RestController
@RequestMapping("/orderservices")
public class OrderServiceController {
    private final OrderServiceService orderServiceService;

    private final OrderServiceDTODao orderServiceDTODao;


    @Autowired
    public OrderServiceController(OrderServiceService orderServiceService, OrderServiceDTODao orderServiceDTODao){
        this.orderServiceService = orderServiceService;
        this.orderServiceDTODao = orderServiceDTODao;
    }

    @GetMapping
    public List<OrderService> getAllOrderService(){
        return orderServiceService.getAllOrderService();
    }

    // ID查詢
    @GetMapping("/{id}")
    public OrderService getOrderServiceById(@PathVariable("id") Integer id){
        return orderServiceService.getOrderServiceById(id);
    }

    @PostMapping
    public OrderService createOrderService(@RequestBody OrderService orderService){
        return orderServiceService.createOrderService(orderService);
    }

    @PutMapping("/{id}")
    public OrderService updateOrderService(@PathVariable("id") Integer id, @RequestBody OrderService updateOrderService){
        return orderServiceService.updateOrderService(id, updateOrderService);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderService(@PathVariable("id") Integer id){
        orderServiceService.deleteOrderService(id);
    }

    @GetMapping("/withServiceName")
    public List<OrderServiceDTO> findOrderServicesWithServiceName(@RequestParam Integer orderServiceId) {
        return orderServiceDTODao.findOrderServicesWithServiceName(orderServiceId);
    }

}
