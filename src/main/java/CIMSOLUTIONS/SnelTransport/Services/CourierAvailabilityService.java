package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.CourierAvailabilityDAO;
import class_objects.AvailablePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierAvailabilityService {

    private CourierAvailabilityDAO courierAvailabilityDAO;

    @Autowired
    public void setInjectedBean(CourierAvailabilityDAO courierAvailabilityDAO) {
        this.courierAvailabilityDAO = courierAvailabilityDAO;
    }

    public List<AvailablePeriod> get(int courierId) {
        return courierAvailabilityDAO.get(courierId);
    }

    public int create(AvailablePeriod availablePeriod) {
        return courierAvailabilityDAO.insert(availablePeriod);
    }
}
