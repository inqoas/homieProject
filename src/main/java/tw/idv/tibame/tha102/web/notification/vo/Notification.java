package tw.idv.tibame.tha102.web.notification.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Notification implements Serializable {

	private static final long serialVersionUID = -3266031502636020108L;

	private Integer notification_id;
	private Integer user_id;
	private Integer violation_id;
	private Integer order_service_id;
	private Integer order_product_id;
	private String notification_content;
	private Timestamp notification_time;
	private Boolean is_read;
	public Integer getNotification_id() {
		return notification_id;
	}
	public void setNotification_id(Integer notification_id) {
		this.notification_id = notification_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getViolation_id() {
		return violation_id;
	}
	public void setViolation_id(Integer violation_id) {
		this.violation_id = violation_id;
	}
	public Integer getOrder_service_id() {
		return order_service_id;
	}
	public void setOrder_service_id(Integer order_service_id) {
		this.order_service_id = order_service_id;
	}
	public Integer getOrder_product_id() {
		return order_product_id;
	}
	public void setOrder_product_id(Integer order_product_id) {
		this.order_product_id = order_product_id;
	}
	public String getNotification_content() {
		return notification_content;
	}
	public void setNotification_content(String notification_content) {
		this.notification_content = notification_content;
	}
	public Timestamp getNotification_time() {
		return notification_time;
	}
	public void setNotification_time(Timestamp notification_time) {
		this.notification_time = notification_time;
	}
	public Boolean getIs_read() {
		return is_read;
	}
	public void setIs_read(Boolean is_read) {
		this.is_read = is_read;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}


