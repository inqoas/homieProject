package tw.idv.tibame.tha102.web.emp.vo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name="BackEndEmp")
@Table(name="emp")
@Setter 
@Getter 
@NoArgsConstructor 
@AllArgsConstructor
@ToString

public class Emp implements Serializable {

	@Serial
	private static final long serialVersionUID = -423055614444130425L;
	@Id
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

}
