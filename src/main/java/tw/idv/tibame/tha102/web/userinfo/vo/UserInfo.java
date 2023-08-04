package tw.idv.tibame.tha102.web.userinfo.vo;

import java.util.Arrays;
import java.sql.Date;

public class UserInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
	private Integer user_id;
    private String user_account;
    private String user_password;
    private String user_name;
    private String user_address;
    private String user_phone;
    private Integer user_gender;
    private Date user_birth;
    private String user_ic;
    private byte[] user_pic;
    private Integer user_status;
    private Integer garbage_coin;
    private Integer seller_identity;
    private String message;
    private boolean success;
    private String user_pic_base64;
    private String user_jwt;
    
    
    
	public String getUser_jwt() {
		return user_jwt;
	}
	public void setUser_jwt(String user_jwt) {
		this.user_jwt = user_jwt;
	}
	public String getUser_pic_base64() {
		return user_pic_base64;
	}
	public void setUser_pic_base64(String user_pic_base64) {
		this.user_pic_base64 = user_pic_base64;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public Integer getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(Integer user_gender) {
		this.user_gender = user_gender;
	}
	public Date getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(Date user_birth) {
		this.user_birth = user_birth;
	}
	public String getUser_ic() {
		return user_ic;
	}
	public void setUser_ic(String user_ic) {
		this.user_ic = user_ic;
	}
	public byte[] getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(byte[] user_pic) {
		this.user_pic = user_pic;
	}
	public Integer getUser_status() {
		return user_status;
	}
	public void setUser_status(Integer user_status) {
		this.user_status = user_status;
	}
	public Integer getGarbage_coin() {
		return garbage_coin;
	}
	public void setGarbage_coin(Integer garbage_coin) {
		this.garbage_coin = garbage_coin;
	}
	public Integer getSeller_identity() {
		return seller_identity;
	}
	public void setSeller_identity(Integer seller_identity) {
		this.seller_identity = seller_identity;
	}
	@Override
	public String toString() {
		return "UserInfo [user_id=" + user_id + ", user_account=" + user_account + ", user_password=" + user_password
				+ ", user_name=" + user_name + ", user_address=" + user_address + ", user_phone=" + user_phone
				+ ", user_gender=" + user_gender + ", user_birth=" + user_birth + ", user_ic=" + user_ic + ", user_pic="
				+ Arrays.toString(user_pic) + ", user_status=" + user_status + ", garbage_coin=" + garbage_coin
				+ ", seller_identity=" + seller_identity + "]";
	}
}
