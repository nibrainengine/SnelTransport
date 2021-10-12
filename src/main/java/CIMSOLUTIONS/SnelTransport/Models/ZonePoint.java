package CIMSOLUTIONS.SnelTransport.Models;

public class ZonePoint {

    //declare variables
    private int id;
    private int zoneId;
    private int index;
    private Double latitude;
    private Double longitude;

    public ZonePoint(){}

    public ZonePoint(int zoneId, Double latitude, Double longitude) {
        this.zoneId = zoneId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
