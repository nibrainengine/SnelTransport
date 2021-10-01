package CIMSOLUTIONS.SnelTransport.Models;

import java.util.List;

public class Courier extends User{

    enum VehicleType{

    }

    //declare variables
    private int id;
    private int kvkNumber;
    private List<Zone> zones;
    private List<VehicleType> vehicles;
    private List<Schedule> schedules;
    private List<AvailablePeriod> availablePeriods;

    //getters
    public int getId() {
        return id;
    }

    public int getKvkNumber() {
        return kvkNumber;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public List<VehicleType> getVehicles() {
        return vehicles;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public List<AvailablePeriod> getAvailablePeriods() {
        return availablePeriods;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setKvkNumber(int kvkNumber) {
        this.kvkNumber = kvkNumber;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public void setVehicles(List<VehicleType> vehicles) {
        this.vehicles = vehicles;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void setAvailablePeriods(List<AvailablePeriod> availablePeriods) {
        this.availablePeriods = availablePeriods;
    }
}
