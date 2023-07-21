package tw.idv.tibame.tha102.web.announcement.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Announcement implements Serializable {

	private static final long serialVersionUID = 590236216699741109L;

	private Integer article_id;
	private Integer emp_id;
	private Timestamp upload_date;
	private String article_title;
	private String article_content;
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public Timestamp getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Timestamp upload_date) {
		this.upload_date = upload_date;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
