package tw.idv.tibame.tha102.web.seller.vo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity(name= "BackEndSeller")
@Table(name = "seller")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Seller implements Serializable{

	@Serial
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
    

    
}
