package tw.idv.tibame.tha102.web.emp.vo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

<<<<<<< HEAD
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

=======
@Entity
@Table(name = "emp")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
>>>>>>> 1d89657432ad6f63bbc9221c92333fca6e75a7e8
public class Emp implements Serializable {

	@Serial
	private static final long serialVersionUID = -423055614444130425L;
	@Id
<<<<<<< HEAD
	private Integer emp_id;
	private String emp_password;
	private String emp_name;
	
=======
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Integer empId;
	@Column(name = "emp_password")
	private String empPassword;
	@Column(name = "emp_name")
	private String empName;
	@Column(name = "emp_email")
	private String empEmail;
	@Column(name = "reset_token")
	private String resetToken;
	@Column(name = "reset_token_expiry")
	private LocalDateTime resetTokenExpiry;

>>>>>>> 1d89657432ad6f63bbc9221c92333fca6e75a7e8
}
