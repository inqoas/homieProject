package tw.idv.tibame.tha102.web.orderProductDetail.vo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
@Entity
@Table(name = "order_product_detail")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProductDetail implements Serializable {
	@Serial
	private static final long serialVersionUID = -1500363324321890760L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="order_product_detail_id")
	private Integer orderProductDetailId;
	@Column(name = "order_product_id")
	private Integer orderProductId;;
	@Column(name ="product_id")
	private Integer productId;
	@Column(name ="product_quantity")
	private Integer productQuantity;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_price")
	private Integer productPrice;
	@Column(name = "product_score")
	private Integer productScore;
	@Column(name = "product_content")
	private String productContent;
}
