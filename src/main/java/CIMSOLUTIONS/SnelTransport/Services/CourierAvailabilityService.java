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

    /**
     * Gets all available periods from a courier
     * @return List<AvailablePeriod>
     */
    public List<AvailablePeriod> get(int courierId) {
        return courierAvailabilityDAO.get(courierId);
    }

    /**
     * Creates new available period of a courier
     * @return int (id of new availablePeriod in database)
     */
    public int create(AvailablePeriod availablePeriod) {
        return courierAvailabilityDAO.insert(availablePeriod);
    }
}
