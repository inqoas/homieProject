package tw.idv.tibame.tha102.web.availableTime.vo;

import java.io.Serializable;
import java.sql.Date;

public class AvailableTime implements Serializable{

	private static final long serialVersionUID = -1583098462677144282L;

	private Integer available_time_id;
    private Integer user_id;
    private Date date;
    private Integer morning;
    private Integer afternoon;
    private Integer night;
	public Integer getAvailable_time_id() {
		return available_time_id;
	}
	public void setAvailable_time_id(Integer available_time_id) {
		this.available_time_id = available_time_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getMorning() {
		return morning;
	}
	public void setMorning(Integer morning) {
		this.morning = morning;
	}
	public Integer getAfternoon() {
		return afternoon;
	}
	public void setAfternoon(Integer afternoon) {
		this.afternoon = afternoon;
	}
	public Integer getNight() {
		return night;
	}
	public void setNight(Integer night) {
		this.night = night;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
