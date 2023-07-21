package tw.idv.tibame.tha102.web.emp.vo;

import java.io.Serializable;

public class Emp implements Serializable {

	private static final long serialVersionUID = -423055614444130425L;

	private Integer emp_id;
	private String emp_password;
	private String emp_name;
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
