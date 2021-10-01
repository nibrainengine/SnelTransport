package CIMSOLUTIONS.SnelTransport.Models;

import java.util.List;

public class Zone {

    //declare variables
    private int id;
    private String zoneTitle;
    private Boolean isApproved;
    private List<ZonePoint> zonePoints;

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
}
