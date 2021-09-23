package class_objects;

import java.util.List;

public class Zone {

    //declare variables
    private int id;
    private String zoneTitle;
    private Boolean isApproved;
    private List<ZonePoint> zonePoints;

    public Zone(int id, String zoneTitle) {
        this.id = id;
        this.zoneTitle = zoneTitle;
    }
}
