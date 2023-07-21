package tw.idv.tibame.tha102.web.serviceCollection.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class ServiceCollection implements Serializable {

	private static final long serialVersionUID = 7532683160545476057L;

	private Integer violation_id;
	private Integer user_id;
	private Integer category_id;
	private Timestamp collect_time;
	public Integer getViolation_id() {
		return violation_id;
	}
	public void setViolation_id(Integer violation_id) {
		this.violation_id = violation_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public Timestamp getCollect_time() {
		return collect_time;
	}
	public void setCollect_time(Timestamp collect_time) {
		this.collect_time = collect_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
