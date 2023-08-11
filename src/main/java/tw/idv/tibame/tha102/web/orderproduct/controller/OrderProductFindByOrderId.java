package tw.idv.tibame.tha102.web.orderproduct.controller;

import org.springframework.web.bind.annotation.*;
import tw.idv.tibame.tha102.web.orderproduct.service.OrderProductService;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;

@RestController
@RequestMapping("/orderproduct")
public class OrderProductFindByOrderId {
    private final OrderProductService service;

    public OrderProductFindByOrderId(OrderProductService service) {
        this.service = service;
    }

    @GetMapping("/find-by-order-product-id")
    public OrderProduct getByOrderProductId(@RequestParam("order_product_id") Integer order_product_id) {
        return service.getByOrderProductId(order_product_id);
    }
}
