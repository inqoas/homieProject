package tw.idv.tibame.tha102.web.userinfo.vo;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Integer product_id;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "")
    private Integer product_price;
    @Column(name = "")
    private Integer product_stock;
    @Column(name = "")
    private Integer product_shipped;
    @Column(name = "")
    private String product_introduction;
    @Column(name = "")
    private byte[] product_picture;
    @Column(name = "")
    private Integer product_category;
    @Column(name = "")
    private Integer product_review_stars;
    @Column(name = "")
    private Integer product_review_count;
    
    
}
