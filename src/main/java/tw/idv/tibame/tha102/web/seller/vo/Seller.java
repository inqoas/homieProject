package tw.idv.tibame.tha102.web.seller.vo;

import java.io.Serializable;

public class Seller implements Serializable{
	private static final long serialVersionUID = -4709007174327349141L;
	private Integer seller_id;
    private Integer user_id;
    private byte[] seller_pcrc;
    private Integer bank_code;
    private String bank_account;
    private String bank_holder_name;
    private Integer total_review_count;
    private Integer total_review_stars;
    
	public Integer getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public byte[] getSeller_pcrc() {
		return seller_pcrc;
	}
	public void setSeller_pcrc(byte[] seller_pcrc) {
		this.seller_pcrc = seller_pcrc;
	}
	public Integer getBank_code() {
		return bank_code;
	}
	public void setBank_code(Integer bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public String getBank_holder_name() {
		return bank_holder_name;
	}
	public void setBank_holder_name(String bank_holder_name) {
		this.bank_holder_name = bank_holder_name;
	}
	public Integer getTotal_review_count() {
		return total_review_count;
	}
	public void setTotal_review_count(Integer total_review_count) {
		this.total_review_count = total_review_count;
	}
	public Integer getTotal_review_stars() {
		return total_review_stars;
	}
	public void setTotal_review_stars(Integer total_review_stars) {
		this.total_review_stars = total_review_stars;
	}
    
}
