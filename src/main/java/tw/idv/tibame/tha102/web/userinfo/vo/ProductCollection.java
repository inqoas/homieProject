package tw.idv.tibame.tha102.web.userinfo.vo;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Component
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductCollection implements Serializable {
	private static final long serialVersionUID = 7407542444075265533L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_collect_id")
	private Integer productCollectId;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "product_id")
	private Integer productId;
	@Column(name = "product_collect_time")
	private Timestamp productCollectTime;
	@Transient
	private String message;
	@Transient
	private boolean success;
}
