package CIMSOLUTIONS.SnelTransport.Mocks;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
/**
 * A fake PickupProduct used only for our fake PickUpHub's.
 */
public class PickupProduct {
    private String productID;
    private Date arrivedOn;

    public PickupProduct() {
        Random random = new Random();
        String randomString = UUID.randomUUID().toString();
        int daysBack = random.nextInt(10);
        // Every day is 86400000 milliseconds, the number of days back is randomized between 1 and 10.
        // Time of the day is always exactly when the method is called.
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
