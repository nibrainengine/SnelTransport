package CIMSOLUTIONS.SnelTransport.Models;

import java.util.List;

public class PickUpHub {

    enum VehicleType{

    }

    //declare variables
    private int id;
    private Address address;
    private Boolean isDisabled;
    private List<OrderItem> orderItems;
    private List<VehicleType> vehicleTypes;
}
