package tw.idv.tibame.tha102.web.service.vo;

import java.io.Serializable;
import java.util.Arrays;

public class Service implements Serializable{
	private static final long serialVersionUID = 6544614811701833624L;

	private Integer service_id;
    private Integer service_id_code;
    private Integer user_id;
    private String service_name;
    private Integer service_price;
    private String service_introduction;
    private byte[] service_picture;
	public Integer getService_id() {
		return service_id;
	}
	public void setService_id(Integer service_id) {
		this.service_id = service_id;
	}
	public Integer getService_id_code() {
		return service_id_code;
	}
	public void setService_id_code(Integer service_id_code) {
		this.service_id_code = service_id_code;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public Integer getService_price() {
		return service_price;
	}
	public void setService_price(Integer service_price) {
		this.service_price = service_price;
	}
	public String getService_introduction() {
		return service_introduction;
	}
	public void setService_introduction(String service_introduction) {
		this.service_introduction = service_introduction;
	}
	public byte[] getService_picture() {
		return service_picture;
	}
	public void setService_picture(byte[] service_picture) {
		this.service_picture = service_picture;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Service [service_id=" + service_id + ", service_id_code=" + service_id_code + ", user_id=" + user_id
				+ ", service_name=" + service_name + ", service_price=" + service_price + ", service_introduction="
				+ service_introduction + ", service_picture=" + Arrays.toString(service_picture) + "]";
	}
    
}
