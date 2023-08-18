package tw.idv.tibame.tha102.web.service.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tw.idv.tibame.tha102.web.area.vo.Area;
import tw.idv.tibame.tha102.web.availableTime.vo.AvailableTime;
import tw.idv.tibame.tha102.web.seller.vo.Seller;

@Entity(name="BackEndService")
@Setter 
@Getter 
@NoArgsConstructor 
@AllArgsConstructor
@ToString

public class Service implements Serializable{
	private static final long serialVersionUID = 6544614811701833624L;
	@Id
	private Integer service_id;
    private Integer service_id_code;
    private Integer user_id;
    private String  service_name;
    private Integer service_price;
    private String  service_introduction;
    private byte[]  service_picture;
    
    private Seller seller;
    private Area areas;
    private AvailableTime availabletimes;
    
    //服務次數
    private Integer service_count;
    //服務總價
    private Integer service_total;

	
    
}
