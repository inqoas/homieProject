package tw.idv.tibame.tha102.web.userinfo.vo;

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
@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
public class Product implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
	private Integer productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private Integer productPrice;
    @Column(name = "product_stock")
    private Integer productStock;
    @Column(name = "product_shipped")
    private Integer productShipped;
    @Column(name = "product_introduction")
    private String productIntroduction;
    @Column(name = "product_picture")
    private byte[] productPicture;
    @Column(name = "product_category")
    private Integer productCategory;
    @Column(name = "product_review_stars")
    private Integer productReview_stars;
    @Column(name = "product_review_count")
    private Integer productReview_count;
    @Transient
	private String message;
	@Transient
	private boolean success;
    
}
