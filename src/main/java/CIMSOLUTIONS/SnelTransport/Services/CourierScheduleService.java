package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.CourierScheduleDAO;
import CIMSOLUTIONS.SnelTransport.DTO.CancelCourierScheduleRequestDTO;
import CIMSOLUTIONS.SnelTransport.Enums.ScheduleStatus;
import CIMSOLUTIONS.SnelTransport.DTO.ScheduleDTO;
import CIMSOLUTIONS.SnelTransport.Models.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourierScheduleService {

    private CourierScheduleDAO courierScheduleDAO;

    @Autowired
    public void setInjectedBean(CourierScheduleDAO courierScheduleDAO){
        this.courierScheduleDAO = courierScheduleDAO;
    }

    /**
     * Gets all active schedules of a specified courier
     * @param courierId the id of the courier whose schedule is required
     * @return List<Schedule>
     */
    public List<Schedule> getScheduled(int courierId) {
        return filterGetOnlyScheduled(courierScheduleDAO.get(courierId));
    }

    /**
     * Gets all schedules of all couriers in half hour segments.
     * @return List<ScheduleDTO>
     */
    public List<ScheduleDTO> getCombinedSchedules() {
        return getCombinedSchedulesPerHalfHour(filterGetOnlyScheduled(courierScheduleDAO.getAllSchedules()));
    }

    /**
     * Gets all schedules of all couriers working in the specified zones in half hour segments.
     * @param zoneFilters- List of zone ids of the zones in which the couriers should work
     * @return List<ScheduleDTO>
     */
    public List<ScheduleDTO> getCombinedSchedulesFilteredByZones(int[] zoneFilters) {
        return getCombinedSchedulesPerHalfHour(filterGetOnlyScheduled(courierScheduleDAO.getAllSchedulesFilteredByZones(zoneFilters)));
    }

    /**
     * Function that changes the given list of schedules into a list of schedules by counting the amount of couriers
     * working during a certain half hour block and adding each block to the list of ScheduleDTOs.
     * @param schedules - List of courier Schedule models
     * @return List<ScheduleDTO>
     */
    public List<ScheduleDTO> getCombinedSchedulesPerHalfHour(List<Schedule> schedules) {
        List<ScheduleDTO> schedulesPerHalfHour = new ArrayList<>();
        for (Schedule schedule : schedules) {
            Date start = schedule.getStartTime();
            Date end = schedule.getEndTime();
            SPLIT_SCHEDULE:
            while (end.getTime() > start.getTime()) {
                //check whether this half hour is already in the list
                for (ScheduleDTO scheduleDTO : schedulesPerHalfHour) {
                    //if this half hour is already in the list, increment the amount of couriers for that block
                    if (scheduleDTO.getStartTime().equals(start)) {
                        scheduleDTO.setNrScheduledCouriers(scheduleDTO.getNrScheduledCouriers() + 1);
                        start = new Date(start.getTime() + (1800 * 1000));
                        continue SPLIT_SCHEDULE;
                    }
                }
                //if it wasn't in the list, add a new half hour schedule
                schedulesPerHalfHour.add(new ScheduleDTO(start, start = new Date(start.getTime() + (1800 * 1000)), 1));
            }
        }
        return schedulesPerHalfHour;
    }

    /**
     * Insert a new CancelCourierScheduleRequestDTO
     * @param cancelRequest consists of the schedule ID and the reason for the cancel request
     */
    public void insertCancelRequest(CancelCourierScheduleRequestDTO cancelRequest) throws Exception {
        courierScheduleDAO.insertCancelRequest(cancelRequest);
    }

    /**
     * Removes canceled schedules from a list of schedules
     * @param schedules list of schedules of a courier
     * @return List<Schedule> filtered list of schedules of courier (that are not cancelled)
     */
    private List<Schedule> filterGetOnlyScheduled(List<Schedule> schedules) {
        schedules.removeIf(schedule -> schedule.getScheduleStatus().equals(ScheduleStatus.Cancelled.name()));
        return schedules;
    }
}
