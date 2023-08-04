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
	@Column
	private Integer order_service_id;
	@Column
	private Integer user_id_seller;
	@Column
	private Integer user_id_buyer;
	@Column
	private Integer order_quantity;
	@Column
	private Integer order_status;
	@Column
	private Integer order_unit_price;
	@Column
	private Integer order_total;
	@Column
	private Timestamp order_placement_time;
	@Column
	private Timestamp order_service_date;
	@Column
	private Integer order_finish_period;
	@Column
	private Timestamp order_service_finish_time;
	@Column
	private Timestamp order_service_finish_date;
	@Column
	private Integer order_add_gc;
	@Column
	private Integer order_deduct_gc;
	@Column
	private Integer order_service_item;
	@Column
	private Integer review_score;
	@Column
	private String review_content;
	@Column
	private Timestamp review_time;
	@Column
	private Timestamp refund_time;

	
	
}
