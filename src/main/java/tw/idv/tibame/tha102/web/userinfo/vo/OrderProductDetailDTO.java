package tw.idv.tibame.tha102.web.userinfo.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;
@Getter
@Setter
@NoArgsConstructor
public class OrderProductDetailDTO {
	private UserInfo userinfo;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private OrderProduct orderProduct;
    
    private List<OrderProductDetail> orderProductDetails;
}
