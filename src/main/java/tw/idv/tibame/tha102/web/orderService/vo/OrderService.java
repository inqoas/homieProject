package tw.idv.tibame.tha102.web.orderService.vo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name = "order_service")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderService implements Serializable {

	@Serial
	private static final long serialVersionUID = -125665335163858379L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_service_id")
	private Integer orderServiceId;
	@Column(name = "user_id_seller")
	private Integer userIdSeller;
	@Column(name = "user_id_buyer")
	private Integer userIdBuyer;
	@Column(name = "order_quantity")
	private Integer orderQuantity;
	@Column(name = "order_status")
	private Integer orderStatus;
	@Column(name = "order_unit_price")
	private Integer orderUnitPrice;
	@Column(name = "order_total")
	private Integer orderTotal;
	@Column(name = "order_placement_time")
	private Timestamp orderPlacementTime;
	@Column(name = "order_service_date")
	private Timestamp orderServiceDate;
	@Column(name = "order_finish_period")
	private Integer orderFinishPeriod;
	@Column(name = "order_service_finish_time")
	private Timestamp orderServiceFinishTime;
	@Column(name = "order_service_finish_date")
	private Timestamp orderServiceFinishDate;
	@Column(name = "order_add_gc")
	private Integer orderAddGc;
	@Column(name = "order_deduct_gc")
	private Integer orderDeductGc;
	@Column(name = "order_service_item")
	private Integer orderServiceItem;
	@Column(name = "review_score")
	private Integer reviewScore;
	@Column(name = "review_content")
	private String reviewContent;
	@Column(name = "review_time")
	private Timestamp reviewTime;
	@Column(name = "refund_time")
	private Timestamp refundTime;

	
	
}
