package tw.idv.tibame.tha102.web.guideline.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Guideline implements Serializable{

	private static final long serialVersionUID = -8682169927039038159L;

	private Integer guideline_id;
    private String guideline_title;
    private String guideline_content;
    private Timestamp guideline_time;
	public Integer getGuideline_id() {
		return guideline_id;
	}
	public void setGuideline_id(Integer guideline_id) {
		this.guideline_id = guideline_id;
	}
	public String getGuideline_title() {
		return guideline_title;
	}
	public void setGuideline_title(String guideline_title) {
		this.guideline_title = guideline_title;
	}
	public String getGuideline_content() {
		return guideline_content;
	}
	public void setGuideline_content(String guideline_content) {
		this.guideline_content = guideline_content;
	}
	public Timestamp getGuideline_time() {
		return guideline_time;
	}
	public void setGuideline_time(Timestamp guideline_time) {
		this.guideline_time = guideline_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
