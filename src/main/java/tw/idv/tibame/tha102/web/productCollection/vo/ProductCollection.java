package tw.idv.tibame.tha102.web.productCollection.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductCollection implements Serializable {

	private static final long serialVersionUID = 7407542444075265533L;

	private Integer product_collect_id;
	private Integer user_id;
	private Integer product_id;
	private Timestamp product_collect_time;
	public Integer getProduct_collect_id() {
		return product_collect_id;
	}
	public void setProduct_collect_id(Integer product_collect_id) {
		this.product_collect_id = product_collect_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Timestamp getProduct_collect_time() {
		return product_collect_time;
	}
	public void setProduct_collect_time(Timestamp product_collect_time) {
		this.product_collect_time = product_collect_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
