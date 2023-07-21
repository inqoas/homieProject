package tw.idv.tibame.tha102.web.orderproduct.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_product")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer order_product_id;
	@Column
    private Integer user_id;
	@Column
    private Integer product_total;
	@Column
    private Integer product_status;
	@Column
    private Integer tracking_number;
	@Column
    private Timestamp delivery_time;
	@Column
    private Timestamp arrival_time;
	@Column
    private Timestamp product_placement_time;
	@Column
    private Timestamp product_finish_date;
	@Column
    private Integer product_add_gc;
	@Column
    private Integer product_deduct_gc;
	@Column
    private Timestamp refund_time;
	@Override
	public String toString() {
		return "OrderProduct [order_product_id=" + order_product_id + ", user_id=" + user_id + ", product_total="
				+ product_total + ", product_status=" + product_status + ", tracking_number=" + tracking_number
				+ ", delivery_time=" + delivery_time + ", arrival_time=" + arrival_time + ", product_placement_time="
				+ product_placement_time + ", product_finish_date=" + product_finish_date + ", product_add_gc="
				+ product_add_gc + ", product_deduct_gc=" + product_deduct_gc + ", refund_time=" + refund_time + "]";
	}
	
}
