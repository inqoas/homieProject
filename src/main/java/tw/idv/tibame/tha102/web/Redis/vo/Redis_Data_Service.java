package tw.idv.tibame.tha102.web.Redis.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="BackEndRedis_Data_Service") 
@Setter 
@Getter 
@NoArgsConstructor 
@AllArgsConstructor
@ToString
public class Redis_Data_Service implements java.io.Serializable{
	@Id
	private String  user_id;
	private Integer seller_id;
	private Integer service_id;
	private String  service_name;
	private Integer service_price;
	private Integer service_count;
	private Integer service_total;
	private String  service_Date;
	private Integer service_DateDetail;
	private Integer service_id_code;
	
}
