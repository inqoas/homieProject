package tw.idv.tibame.tha102.web.advertise.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class advertise implements Serializable{

	private static final long serialVersionUID = 5707255555600962014L;
	
	private Integer advertise_id;
    private Integer user_id;
    private Timestamp advertise_start_time;
    private Timestamp advertise_end_time;
	public Integer getAdvertise_id() {
		return advertise_id;
	}
	public void setAdvertise_id(Integer advertise_id) {
		this.advertise_id = advertise_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Timestamp getAdvertise_start_time() {
		return advertise_start_time;
	}
	public void setAdvertise_start_time(Timestamp advertise_start_time) {
		this.advertise_start_time = advertise_start_time;
	}
	public Timestamp getAdvertise_end_time() {
		return advertise_end_time;
	}
	public void setAdvertise_end_time(Timestamp advertise_end_time) {
		this.advertise_end_time = advertise_end_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
