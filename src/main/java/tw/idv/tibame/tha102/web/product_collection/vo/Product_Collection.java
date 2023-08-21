package tw.idv.tibame.tha102.web.product_collection.vo;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Product_Collection {
	//product_collect_id, user_id, product_id, product_collect_time
	@Id
	private Integer product_collect_id;
	private Integer user_id;
	private Integer product_id;
	private Timestamp product_collect_time;

}
