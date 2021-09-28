package CIMSOLUTIONS.SnelTransport.Mock;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class PickupProduct {
    private String productID;
    private Date arrivedOn;

    public PickupProduct() {
        Random random = new Random();
        String randomString = UUID.randomUUID().toString();
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
