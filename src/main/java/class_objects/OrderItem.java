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
    private String orderStatus;
    private Address deliveryAddress;

    public OrderItem(int id, int quantity, Double price, Date eta, Product product, String orderStatus, Address deliveryAddress) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.eta = eta;
        this.product = product;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Date getEta() {
        return eta;
    }

    public Product getProduct() {
        return product;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }
}
