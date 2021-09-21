package class_objects;

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
}
