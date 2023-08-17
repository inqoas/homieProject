package tw.idv.tibame.tha102.web.seller.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.idv.tibame.tha102.web.seller.service.SellerService;
import tw.idv.tibame.tha102.web.seller.vo.Seller;


@RestController
@RequestMapping("/sellers")
public class SellerController{
    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    // 新增
    @PostMapping
    public Seller createSeller(@RequestBody Seller seller){
        return sellerService.createSeller(seller);
    }

    // 更新
    @PutMapping("/{id}")
    public  Seller updateSeller(@PathVariable("id") Integer id, @RequestBody Seller updateSeller){
        return sellerService.updateSeller(id, updateSeller);
    }

    // 依userId查詢
    @GetMapping("/by-user/{userId}")
    public Seller getSellerByUserId(@PathVariable("userId") Integer userId){
        return sellerService.getSellerByUserId(userId);
    }

    @GetMapping("/pcrc")
    public void getPcrcImage(HttpServletResponse response, @RequestParam("user_id") Integer userId){
        byte[] pic = sellerService.getPcrcByUserId(userId);
        if(pic != null){
            response.setContentType("image/jpeg");
            try {
                response.getOutputStream().write(pic);
                response.getOutputStream().flush();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

