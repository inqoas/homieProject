package tw.idv.tibame.tha102.web.area.vo;

import java.io.Serializable;

public class Area implements Serializable{

	private static final long serialVersionUID = -4413771299294634447L;
	
	private Integer area_id;
    private Integer user_id;
    private String area_name;
	public Integer getArea_id() {
		return area_id;
	}
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
    
    
}
