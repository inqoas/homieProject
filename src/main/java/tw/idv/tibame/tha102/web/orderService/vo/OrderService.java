package tw.idv.tibame.tha102.web.orderService.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderService implements Serializable {

	private static final long serialVersionUID = -125665335163858379L;

	private Integer order_service_id;
	private Integer user_id_seller;
	private Integer user_id_buyer;
	private Integer order_quantity;
	private Integer order_status;
	private Integer order_unit_price;
	private Integer order_total;
	private Timestamp order_placement_time;
	private Timestamp order_service_date;
	private Integer order_finish_period;
	private Timestamp order_service_finish_time;
	private Timestamp order_service_finish_date;
	private Integer order_add_gc;
	private Integer order_deduct_gc;
	private Integer order_service_item;
	private Integer review_score;
	private String review_content;
	private Timestamp review_time;
	private Timestamp refund_time;
	public Integer getOrder_service_id() {
		return order_service_id;
	}
	public void setOrder_service_id(Integer order_service_id) {
		this.order_service_id = order_service_id;
	}
	public Integer getUser_id_seller() {
		return user_id_seller;
	}
	public void setUser_id_seller(Integer user_id_seller) {
		this.user_id_seller = user_id_seller;
	}
	public Integer getUser_id_buyer() {
		return user_id_buyer;
	}
	public void setUser_id_buyer(Integer user_id_buyer) {
		this.user_id_buyer = user_id_buyer;
	}
	public Integer getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(Integer order_quantity) {
		this.order_quantity = order_quantity;
	}
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	public Integer getOrder_unit_price() {
		return order_unit_price;
	}
	public void setOrder_unit_price(Integer order_unit_price) {
		this.order_unit_price = order_unit_price;
	}
	public Integer getOrder_total() {
		return order_total;
	}
	public void setOrder_total(Integer order_total) {
		this.order_total = order_total;
	}
	public Timestamp getOrder_placement_time() {
		return order_placement_time;
	}
	public void setOrder_placement_time(Timestamp order_placement_time) {
		this.order_placement_time = order_placement_time;
	}
	public Timestamp getOrder_service_date() {
		return order_service_date;
	}
	public void setOrder_service_date(Timestamp order_service_date) {
		this.order_service_date = order_service_date;
	}
	public Integer getOrder_finish_period() {
		return order_finish_period;
	}
	public void setOrder_finish_period(Integer order_finish_period) {
		this.order_finish_period = order_finish_period;
	}
	public Timestamp getOrder_service_finish_time() {
		return order_service_finish_time;
	}
	public void setOrder_service_finish_time(Timestamp order_service_finish_time) {
		this.order_service_finish_time = order_service_finish_time;
	}
	public Timestamp getOrder_service_finish_date() {
		return order_service_finish_date;
	}
	public void setOrder_service_finish_date(Timestamp order_service_finish_date) {
		this.order_service_finish_date = order_service_finish_date;
	}
	public Integer getOrder_add_gc() {
		return order_add_gc;
	}
	public void setOrder_add_gc(Integer order_add_gc) {
		this.order_add_gc = order_add_gc;
	}
	public Integer getOrder_deduct_gc() {
		return order_deduct_gc;
	}
	public void setOrder_deduct_gc(Integer order_deduct_gc) {
		this.order_deduct_gc = order_deduct_gc;
	}
	public Integer getOrder_service_item() {
		return order_service_item;
	}
	public void setOrder_service_item(Integer order_service_item) {
		this.order_service_item = order_service_item;
	}
	public Integer getReview_score() {
		return review_score;
	}
	public void setReview_score(Integer review_score) {
		this.review_score = review_score;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public Timestamp getReview_time() {
		return review_time;
	}
	public void setReview_time(Timestamp review_time) {
		this.review_time = review_time;
	}
	public Timestamp getRefund_time() {
		return refund_time;
	}
	public void setRefund_time(Timestamp refund_time) {
		this.refund_time = refund_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
