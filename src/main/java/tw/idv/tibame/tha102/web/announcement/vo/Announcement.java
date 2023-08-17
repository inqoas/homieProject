package tw.idv.tibame.tha102.web.announcement.vo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="announcement")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Announcement implements Serializable {

	@Serial
	private static final long serialVersionUID = 590236216699741109L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Integer articleId;
	@Column(name = "emp_id")
	private Integer empId;
	@Column(name = "upload_date")
	private Timestamp uploadDate;
	@Column(name = "article_title")
	private String articleTitle;
	@Column(name = "article_content")
	private String articleContent;
}
