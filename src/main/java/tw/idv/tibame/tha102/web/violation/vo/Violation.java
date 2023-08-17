package tw.idv.tibame.tha102.web.violation.vo;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "violation")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Violation implements Serializable {

	@Serial
	private static final long serialVersionUID = -3673326339769633767L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "violation_id")
	private Integer violationId;
	@Column(name = "user_id_report")
	private Integer userIdReport;
	@Column(name = "user_id_reported")
	private Integer userIdReported;
	@Column(name = "report_direction")
	private Integer reportDirection;
	@Column(name = "violation_category")
	private Integer violationCategory;
	@Column(name = "violation_content")
	private String violationContent;
	@Column(name = "violation_pic1")
	private byte[] violationPic1;
	@Column(name = "violation_pic2")
	private byte[] violationPic2;
	@Column(name = "violation_pic3")
	private byte[] violationPic3;
	@Column(name = "violation_pic4")
	private byte[] violationPic4;
	@Column(name = "violation_pic5")
	private byte[] violationPic5;
	@Column(name = "violation_time")
	private Timestamp violationTime;
	@Column(name = "violation_status")
	private Integer violationStatus;
}
