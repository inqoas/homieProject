package tw.idv.tibame.tha102.web.emp.vo;

import java.io.Serializable;

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

@Entity(name="BackEndEmp")
@Setter 
@Getter 
@NoArgsConstructor 
@AllArgsConstructor
@ToString

public class Emp implements Serializable {

	private static final long serialVersionUID = -423055614444130425L;
	@Id
	private Integer emp_id;
	private String emp_password;
	private String emp_name;
	
}
