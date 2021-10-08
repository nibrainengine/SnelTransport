package CIMSOLUTIONS.SnelTransport.Models;

import java.util.ArrayList;
import java.util.List;

public class Zone {

    //declare variables
    private int id;
    private String zoneTitle;
    private Boolean isApproved;
    private List<ZonePoint> zonePoints = new ArrayList<>();

    public Zone(){}

    public Zone(int id, String zoneTitle) {
        this.id = id;
        this.zoneTitle = zoneTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZoneTitle() {
        return zoneTitle;
    }

    public void setZoneTitle(String zoneTitle) {
        this.zoneTitle = zoneTitle;
    }

    public List<ZonePoint> getZonePoints() {
        return zonePoints;
    }

    public void setZonePoints(List<ZonePoint> zonePoints) {
        this.zonePoints = zonePoints;
    }

    public void addZonePoint(ZonePoint zonePoint){this.zonePoints.add(zonePoint); }
}
