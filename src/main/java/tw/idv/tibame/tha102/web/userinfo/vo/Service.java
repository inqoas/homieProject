package tw.idv.tibame.tha102.web.userinfo.vo;

import java.io.Serializable;

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
@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
public class Service implements Serializable{
	private static final long serialVersionUID = 6544614811701833624L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private Integer serviceId;
	@Column(name = "service_id_code")
    private Integer serviceIdCode;
	@Column(name = "user_id")
    private Integer userId;
	@Column(name = "service_name")
    private String serviceName;
	@Column(name = "service_price")
    private Integer servicePrice;
	@Column(name = "service_introduction")
    private String serviceIntroduction;
	@Column(name = "service_picture")
    private byte[] servicePicture;
	@Transient
	private String message;
	@Transient
	private boolean success;
}
