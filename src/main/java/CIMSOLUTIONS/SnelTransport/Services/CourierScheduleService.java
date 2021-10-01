package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.CourierScheduleDAO;
import CIMSOLUTIONS.SnelTransport.class_objects.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierScheduleService {

    private CourierScheduleDAO courierScheduleDAO;

    @Autowired
    public void setInjectedBean(CourierScheduleDAO courierScheduleDAO){
        this.courierScheduleDAO = courierScheduleDAO;
    }

    /**
     * Gets all schedules of a specified courier.
     * @param courierId the id of the courier whose schedule is required
     * @return List<Schedule>
     */
    public List<Schedule> get(int courierId) {
        return courierScheduleDAO.get(courierId);
    }
}
