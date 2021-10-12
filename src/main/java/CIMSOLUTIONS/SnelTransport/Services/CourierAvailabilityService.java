package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.CourierAvailabilityDAO;
import CIMSOLUTIONS.SnelTransport.Models.AvailablePeriod;
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
     * @param courierId identification of a courier,
     * @return List<AvailablePeriod> AvailablePeriod contains data on when a courier is available
     */
    public List<AvailablePeriod> get(int courierId) throws Exception {
        return courierAvailabilityDAO.get(courierId);
    }

    /**
     * Creates new available period of a courier
     * @param availablePeriod contains data on when a courier is available
     * @return int (id of new availablePeriod in database)
     */
    public int create(AvailablePeriod availablePeriod) throws Exception {
        return courierAvailabilityDAO.insert(availablePeriod);
    }
    /**
     * Put call that approves an available period
     * @param id primary of the id that will be changed
     */
    public void approve(int id) throws Exception {
        courierAvailabilityDAO.approve(id);
    }
}
