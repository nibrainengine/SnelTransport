package class_objects;

import java.util.Date;

public class OrderItem {

    enum OrderStatus{

    }

    //declare variables
    private int id;
    private int quantity;
    private Double price;
    private Date eta;
    private Product product;
    private OrderStatus orderStatus;
    private Address deliveryAddress;
}
