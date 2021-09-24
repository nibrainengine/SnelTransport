package CIMSOLUTIONS.SnelTransport.Mock;

import class_objects.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PickupAPI {
    private List<PickupProduct> products;

    public PickupAPI() {
        Random random = new Random();
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
