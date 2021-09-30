package CIMSOLUTIONS.SnelTransport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@RestController
@SpringBootApplication
public class SnelTransportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnelTransportApplication.class, args);
	}
}
