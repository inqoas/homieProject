package tw.idv.tibame.tha102;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("tw.idv.tibame.tha102.*.controller")
public class RealHomieApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealHomieApplication.class, args);
	}

}
