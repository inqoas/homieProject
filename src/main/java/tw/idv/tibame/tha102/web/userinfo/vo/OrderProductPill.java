package tw.idv.tibame.tha102.web.userinfo.vo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderProductPill {
	private Integer productId;
	private String productName;
	private Integer productQuantity;
	private Integer productPrice;
	private Date productPlacementTime;
	private Integer productStatus;
	private boolean success;
	private String message;
}
