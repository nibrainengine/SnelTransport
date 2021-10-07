package CIMSOLUTIONS.SnelTransport.Mocks;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A fake PickupAPI, this is the model class for a non existing API that will be used to feed the data of its fake controller class.
 */
public class MockPickupAPI {
    private List<PickupProduct> products;

    public MockPickupAPI() {
        Random random = new Random();
        //The list of products can be between 0 and 10, just like a real PickUpHub, it can be empty.
        int numberOfRandomProducts = random.nextInt(10);
        ArrayList<PickupProduct> sampleProducts = new ArrayList<>();
        for(int i = 0; i < numberOfRandomProducts; i++){
            sampleProducts.add(new PickupProduct());
        }
        this.products = sampleProducts;
    }

    public List<PickupProduct> getProducts() {
        return products;
    }
}
