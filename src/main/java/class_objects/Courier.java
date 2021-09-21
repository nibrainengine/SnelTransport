package class_objects;

import java.util.List;

public class Courier extends User{

    //declare variables
    private int id;
    private int kvkNumber;
    private List<CourierZonePoint> courierZonePoints;
    private List<Vehicle> vehicles;
    private List<Schedule> schedules;
    private List<AvailablePeriod> availablePeriods;
}
