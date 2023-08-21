package tw.idv.tibame.tha102.web.Service_Collection.vo;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Component
public class Service_Collection {
	//violation_id, user_id, category_id, collect_time
	@Id
	private Integer violation_id;
	private Integer user_id;
	private Integer category_id;
	private Timestamp collect_time;
}
