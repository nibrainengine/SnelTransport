package class_objects;

import java.util.List;

public class PickUpHub {

    enum VehicleType{

    }

    public PickUpHub(Address address, Boolean isDisabled, List<OrderItem> orderItems, List<VehicleType> vehicleTypes, String url) {
        this.address = address;
        this.isDisabled = isDisabled;
        this.orderItems = orderItems;
        this.vehicleTypes = vehicleTypes;
        this.url = url;
    }

    public PickUpHub(Address address, String url) {
        this.address = address;
        this.isDisabled = false;
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

    //declare variables
    private int id;
    private Address address;
    private Boolean isDisabled;
    private List<OrderItem> orderItems;
    private List<VehicleType> vehicleTypes;
    private String url;
}
