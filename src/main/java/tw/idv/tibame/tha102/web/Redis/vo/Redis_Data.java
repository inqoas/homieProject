package tw.idv.tibame.tha102.web.Redis.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="BackEndRedisData") 
@Setter 
@Getter 
@NoArgsConstructor 
@AllArgsConstructor
@ToString
public class Redis_Data implements java.io.Serializable{
	@Id
	private String  user_id;
	private String  product_name;
	private Integer product_id;
	private Integer product_price;
	private Integer product_count;
	private Integer product_total;

	
}
