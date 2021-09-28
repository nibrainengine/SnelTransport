package class_objects;

import java.util.Date;

public class AvailablePeriod {

    //declare variables
    private int id;
    private int courierId;
    private Date startTime;
    private Date endTime;
    private double price;
    private boolean isApproved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    @Override
    public String toString() {
        return "AvailablePeriod{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", isApproved=" + isApproved +
                '}';
    }
}
