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

//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
//		PickupAPI testPickup = new PickupAPI();
//		for(PickupProduct product : testPickup.getProducts()){
//			System.out.println("ProductID: " + product.getProductID() + " arrived on date: " + formatter.format(product.getArrivedOn()));
//		}
//		PickupData pickupData = new PickupData();
//		System.out.println(pickupData.getMockedPickupData());

	}



}
