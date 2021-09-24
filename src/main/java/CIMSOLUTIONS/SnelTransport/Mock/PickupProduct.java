package CIMSOLUTIONS.SnelTransport.Mock;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;

public class PickupProduct {
    private String productID;
    private Date arrivedOn;

    public PickupProduct() {
        Random random = new Random();
        byte[] array = new byte[6]; // productID is 6 random characters
        random.nextBytes(array);
        String randomString = new String(array, StandardCharsets.UTF_8);
        int daysBack = random.nextInt(10);
        // Every day is 86400000 milliseconds, I grab the current time when generating minus daysBack to generate the date on which this product arrived
        this.arrivedOn = new Date(System.currentTimeMillis() - (daysBack*86400000)) ;
        this.productID  = randomString;
    }

    public String getProductID() {
        return productID;
    }

    public Date getArrivedOn() {
        return arrivedOn;
    }
}
