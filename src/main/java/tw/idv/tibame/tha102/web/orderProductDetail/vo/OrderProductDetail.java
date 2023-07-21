package tw.idv.tibame.tha102.web.orderProductDetail.vo;

import java.io.Serializable;

public class OrderProductDetail implements Serializable {

	private static final long serialVersionUID = -1500363324321890760L;

	private Integer order_product_detail_id;
	private Integer order_product_id;
	private Integer product_id;
	private Integer product_quantity;
	private String product_name;
	private Integer product_price;
	private Integer product_score;
	private String product_content;
	public Integer getOrder_product_detail_id() {
		return order_product_detail_id;
	}
	public void setOrder_product_detail_id(Integer order_product_detail_id) {
		this.order_product_detail_id = order_product_detail_id;
	}
	public Integer getOrder_product_id() {
		return order_product_id;
	}
	public void setOrder_product_id(Integer order_product_id) {
		this.order_product_id = order_product_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(Integer product_quantity) {
		this.product_quantity = product_quantity;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getProduct_price() {
		return product_price;
	}
	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}
	public Integer getProduct_score() {
		return product_score;
	}
	public void setProduct_score(Integer product_score) {
		this.product_score = product_score;
	}
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
