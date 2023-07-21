package tw.idv.tibame.tha102.web.violation.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Violation implements Serializable {

	private static final long serialVersionUID = -3673326339769633767L;

	private Integer violation_id;
	private Integer user_id_report;
	private Integer user_id_reported;
	private Integer report_direction;
	private Integer violation_category;
	private String violation_content;
	private byte[] violation_pic1;
	private byte[] violation_pic2;
	private byte[] violation_pic3;
	private byte[] violation_pic4;
	private byte[] violation_pic5;
	private Timestamp violation_time;
	private Integer violation_status;
	public Integer getViolation_id() {
		return violation_id;
	}
	public void setViolation_id(Integer violation_id) {
		this.violation_id = violation_id;
	}
	public Integer getUser_id_report() {
		return user_id_report;
	}
	public void setUser_id_report(Integer user_id_report) {
		this.user_id_report = user_id_report;
	}
	public Integer getUser_id_reported() {
		return user_id_reported;
	}
	public void setUser_id_reported(Integer user_id_reported) {
		this.user_id_reported = user_id_reported;
	}
	public Integer getReport_direction() {
		return report_direction;
	}
	public void setReport_direction(Integer report_direction) {
		this.report_direction = report_direction;
	}
	public Integer getViolation_category() {
		return violation_category;
	}
	public void setViolation_category(Integer violation_category) {
		this.violation_category = violation_category;
	}
	public String getViolation_content() {
		return violation_content;
	}
	public void setViolation_content(String violation_content) {
		this.violation_content = violation_content;
	}
	public byte[] getViolation_pic1() {
		return violation_pic1;
	}
	public void setViolation_pic1(byte[] violation_pic1) {
		this.violation_pic1 = violation_pic1;
	}
	public byte[] getViolation_pic2() {
		return violation_pic2;
	}
	public void setViolation_pic2(byte[] violation_pic2) {
		this.violation_pic2 = violation_pic2;
	}
	public byte[] getViolation_pic3() {
		return violation_pic3;
	}
	public void setViolation_pic3(byte[] violation_pic3) {
		this.violation_pic3 = violation_pic3;
	}
	public byte[] getViolation_pic4() {
		return violation_pic4;
	}
	public void setViolation_pic4(byte[] violation_pic4) {
		this.violation_pic4 = violation_pic4;
	}
	public byte[] getViolation_pic5() {
		return violation_pic5;
	}
	public void setViolation_pic5(byte[] violation_pic5) {
		this.violation_pic5 = violation_pic5;
	}
	public Timestamp getViolation_time() {
		return violation_time;
	}
	public void setViolation_time(Timestamp violation_time) {
		this.violation_time = violation_time;
	}
	public Integer getViolation_status() {
		return violation_status;
	}
	public void setViolation_status(Integer violation_status) {
		this.violation_status = violation_status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
