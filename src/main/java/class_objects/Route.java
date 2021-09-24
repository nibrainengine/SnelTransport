package class_objects;

import java.util.List;

public class Route {

    //declare variables
    private int id;
    private int index;
    private List<OrderItem> orderItems;
    private Address startAddress;
    private Address endAddress;

    public Route(){

    }

    public Route(int id, int index, Address startAddress, Address endAddress) {
        this.id = id;
        this.index = index;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
    }

    public int getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Address getStartAddress() {
        return startAddress;
    }

    public Address getEndAddress() {
        return endAddress;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
