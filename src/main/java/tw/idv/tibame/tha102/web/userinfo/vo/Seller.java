package tw.idv.tibame.tha102.web.userinfo.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Component
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Seller implements Serializable{
	private static final long serialVersionUID = -4709007174327349141L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_id")
	private Integer sellerId;
	@Column(name = "user_id")
    private Integer userId;
	@Column(name = "seller_pcrc")
    private byte[] sellerPcrc;
	@Column(name = "bank_code")
    private Integer bankCode;
	@Column(name = "bank_account")
    private String bankAccount;
	@Column(name = "bank_holder_name")
    private String bankHolderName;
	@Column(name = "total_review_count")
    private Integer totalReviewCount;
	@Column(name = "total_review_stars")
    private Integer totalReviewStars;
	@Transient
	private String message;
	@Transient
	private boolean success;
	@Transient
	private String sellerPcrcBase64;
    
}
