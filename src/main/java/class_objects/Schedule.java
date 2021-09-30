package class_objects;

import java.util.Date;
import java.util.List;

public class Schedule {

    //declare variables
    private int id;
    private Date startTime;
    private Date endTime;
    private List<Route> routes;

    //getters and setters
    public int getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
