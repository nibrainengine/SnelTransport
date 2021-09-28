package CIMSOLUTIONS.SnelTransport;

import CIMSOLUTIONS.SnelTransport.Mock.PickupAPI;
import CIMSOLUTIONS.SnelTransport.Mock.PickupProduct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class SnelTransportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnelTransportApplication.class, args);


	}



}
