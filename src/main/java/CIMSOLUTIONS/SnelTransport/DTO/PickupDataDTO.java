package CIMSOLUTIONS.SnelTransport.DTO;

import CIMSOLUTIONS.SnelTransport.Mocks.PickupProduct;
import class_objects.Address;

import java.util.Collections;
import java.util.List;

public class PickupDataDTO {
    private Address address;
    private String url;
    private List<PickupProduct> products;

    public PickupDataDTO(Address address, String url, List<PickupProduct> products) {
        this.address = address;
        this.url = url;
        if(products == null){
            this.products = Collections.emptyList();
        }
        else{
            this.products = products;
        }

    }

    public PickupDataDTO(Address address, String url) {
        this.address = address;
        this.url = url;
        this.products = Collections.emptyList();
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setProducts(List<PickupProduct> products) {
        this.products = products;
    }

    public Address getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    public List<PickupProduct> getProducts() {
        return products;
    }
}
