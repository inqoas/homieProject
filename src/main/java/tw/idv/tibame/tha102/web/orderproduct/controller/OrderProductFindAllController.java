package tw.idv.tibame.tha102.web.orderproduct.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.tibame.tha102.web.orderproduct.service.OrderProductService;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;

import java.util.List;

@RestController
@RequestMapping("/orderproduct")
public class OrderProductFindAllController {
    private final OrderProductService service;

    public OrderProductFindAllController(OrderProductService service) {
        this.service = service;
    }

    @GetMapping("/find-all")
    public List<OrderProduct> findAll() {
        return service.findAll();
    }
}
