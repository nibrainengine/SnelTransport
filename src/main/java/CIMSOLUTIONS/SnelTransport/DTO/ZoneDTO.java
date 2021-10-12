package CIMSOLUTIONS.SnelTransport.DTO;

public class ZoneDTO {

    //declare variables
    private int zoneId;
    private String zoneTitle;
    private int courierId;
    private String courierName;


    //getters and setters
    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneTitle() {
        return zoneTitle;
    }

    public void setZoneTitle(String zoneTitle) {
        this.zoneTitle = zoneTitle;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierid) {
        this.courierId = courierid;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }
}
