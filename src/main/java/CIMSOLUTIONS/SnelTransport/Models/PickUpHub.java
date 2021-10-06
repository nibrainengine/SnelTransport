package CIMSOLUTIONS.SnelTransport.Models;

import java.util.List;

public class PickUpHub {
    //declare variables
    private int id;
    private Address address;
    private Boolean isDisabled;
    private List<OrderItem> orderItems;
    private List<VehicleType> vehicleTypes;
    private String url;

    enum VehicleType{

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisabled(Boolean disabled) {
        isDisabled = disabled;
    }

    public PickUpHub(Address address, Boolean isDisabled, String url) {
        this.address = address;
        this.isDisabled = isDisabled;
        this.url = url;
    }

    public PickUpHub() {
        this.isDisabled = false;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getDisabled() {
        return isDisabled;
    }

    public String getUrl() {
        return url;
    }

    public Address getAddress() {
        return address;
    }


}
