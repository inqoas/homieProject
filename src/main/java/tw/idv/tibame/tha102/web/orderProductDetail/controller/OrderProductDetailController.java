package tw.idv.tibame.tha102.web.orderProductDetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.idv.tibame.tha102.web.orderProductDetail.service.OrderProductDetailService;
import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;

import java.util.List;

@RestController
@RequestMapping("/orderproductdetail")
public class OrderProductDetailController {
    private final OrderProductDetailService orderProductDetailService;

    @Autowired
    public OrderProductDetailController(OrderProductDetailService orderProductDetailService) {
        this.orderProductDetailService = orderProductDetailService;
    }

    // 所有訂單
    @GetMapping
    public List<OrderProductDetail> getAllOrderProductDetails() {
        return orderProductDetailService.getAllOrderProductDetails();
    }

    // ID查詢
    @GetMapping("/{id}")
    public OrderProductDetail getOrderProductDetailById(@PathVariable("id") Integer id) {
        return orderProductDetailService.getOrderProductDetailById(id);
    }

    // 新增
    @PostMapping
    public OrderProductDetail createOrderProductDetail(@RequestBody OrderProductDetail orderProductDetail) {
        return orderProductDetailService.createOrderProductDetail(orderProductDetail);
    }

    // 更新
    @PutMapping("/{id}")
    public OrderProductDetail updateOrderProductDetail(
            @PathVariable("id") Integer id,
            @RequestBody OrderProductDetail updatedOrderProductDetail
    ) {
        return orderProductDetailService.updateOrderProductDetail(id, updatedOrderProductDetail);
    }

    // 刪除
    @DeleteMapping("/{id}")
    public void deleteOrderProductDetail(@PathVariable("id") Integer id) {
        orderProductDetailService.deleteOrderProductDetail(id);
    }

    @GetMapping("/find-order-detail-by-order-product-id")
    public List<OrderProductDetail> getOrderProductDetailsByOrderProductId(@RequestParam("order_product_id") Integer orderProductId) {
        return orderProductDetailService.getOrderProductDetailsByOrderProductId(orderProductId);
    }
}
