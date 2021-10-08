package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.CourierScheduleDAO;
import CIMSOLUTIONS.SnelTransport.DTO.CancelCourierScheduleRequestDTO;
import CIMSOLUTIONS.SnelTransport.Enums.ScheduleStatus;
import CIMSOLUTIONS.SnelTransport.Models.Schedule;
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
     * Gets all active schedules of a specified courier, canceled schedules are filtered out
     * @param courierId the id of the courier whose schedule is required
     * @return List<Schedule>
     */
    public List<Schedule> getScheduled(int courierId) {
        List<Schedule> schedules = courierScheduleDAO.get(courierId);
        schedules.removeIf(schedule -> schedule.getScheduleStatus().equals(ScheduleStatus.Cancelled.name()));
        return schedules;
    }

    /**
     * Insert a new CancelCourierScheduleRequestDTO
     * @param cancelRequest consists of the schedule ID and the reason for the cancel request
     */
    public void insertCancelRequest(CancelCourierScheduleRequestDTO cancelRequest) throws Exception {
        courierScheduleDAO.insertCancelRequest(cancelRequest);
    }
}
