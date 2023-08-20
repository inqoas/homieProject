package tw.idv.tibame.tha102.web.orderProductDetail.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Component
public class OrderProductDetail implements Serializable {
	private static final long serialVersionUID = 7270801586501222209L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_detail_id")
	private Integer orderProductDetailId;
	
	@Column(name = "order_product_id")
	private Integer orderProductId;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "product_quantity")
	private Integer productQuantity;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_price")
	private Integer productPrice;
	
	@Column(name = "product_score")
	private Integer productScore;
	
	@Column(name = "product_content")
	private String productContent;
	
	@Transient
	private boolean success;
	
	@Transient
	private String message;
}

