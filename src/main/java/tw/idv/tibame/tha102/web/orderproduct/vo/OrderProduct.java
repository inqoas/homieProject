package tw.idv.tibame.tha102.web.orderproduct.vo;

import java.io.Serial;
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
import lombok.ToString;

@Entity
@Table(name = "order_product")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProduct implements Serializable{
	@Serial
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
	
}
