package tw.idv.tibame.tha102.web.userinfo.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ServiceCollection implements Serializable {
	private static final long serialVersionUID = 7532683160545476057L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_collection_id")
	private Integer serviceCollectionId;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "service_id")
	private Integer serviceId;
	@Column(name = "collect_time")
	private Timestamp collectTime;
	@Transient
	private String message;
	@Transient
	private boolean success;

}
