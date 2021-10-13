package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.DTO.CancelCourierScheduleRequestDTO;
import CIMSOLUTIONS.SnelTransport.Enums.ScheduleStatus;
import CIMSOLUTIONS.SnelTransport.Models.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CourierScheduleDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Function that returns a list of schedules from a specific courier.
     * @param courierId the id of the courier whose schedule is required
     * @return List<Schedule>
     */
    public List<Schedule> get(int courierId) {
        String query =  "SELECT DISTINCT cs.id as id, cs.start as startTime, cs.[end] as endTime, " +
                            "iif(ccs.approved IS NULL, '"+ ScheduleStatus.Scheduled.name() +"', " +
                                "iif(ccs.approved = 1, '"+ ScheduleStatus.Cancelled.name() +"', '"+
                                ScheduleStatus.CancelRequest.name() +"')) AS scheduleStatus " +
                        "FROM courierSchedule cs FULL OUTER JOIN canceledCourierSchedule ccs on cs.id = ccs.courierScheduleId " +
                        "WHERE cs.courierID = " + +courierId;
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Schedule.class));
    }

    /**
     * Function that returns a list of schedules all couriers.
     * @return List<Schedule>
     */
    public List<Schedule> getAllSchedules()  {
        String scheduleQuery =  "SELECT DISTINCT cs.start as startTime, cs.[end] as endTime, " +
                                    "iif(ccs.approved IS NULL, '"+ ScheduleStatus.Scheduled.name() +"', " +
                                    "iif(ccs.approved = 1, '"+ ScheduleStatus.Cancelled.name() +"', '"+
                                    ScheduleStatus.CancelRequest.name() +"')) AS scheduleStatus " +
                                "FROM courierSchedule cs FULL OUTER JOIN canceledCourierSchedule ccs on cs.id = ccs.courierScheduleId";
        return jdbcTemplate.query(scheduleQuery, BeanPropertyRowMapper.newInstance(Schedule.class));
    }

    /**
     * Function that returns a list of schedules of the amount of couriers working at the specified zones.
     * @param zoneFilters - List of zone ids of the zones in which the couriers should work
     * @return List<Schedule>
     */
    public List<Schedule> getAllSchedulesFilteredByZones(int[] zoneFilters)  {
        if(zoneFilters.length < 1){
            return new ArrayList<>();
        }
        String reformatZoneIds = Arrays.toString(zoneFilters);
        reformatZoneIds = reformatZoneIds.replace("[", "(").replace("]", ")");
        String scheduleQuery =  "SELECT DISTINCT cs.start as startTime, cs.[end] as endTime, " +
                                    "iif(ccs.approved IS NULL, '"+ ScheduleStatus.Scheduled.name() +"', " +
                                    "iif(ccs.approved = 1, '"+ ScheduleStatus.Cancelled.name() +"', '"+
                                    ScheduleStatus.CancelRequest.name() +"')) AS scheduleStatus " +
                                "FROM courierSchedule cs FULL OUTER JOIN canceledCourierSchedule ccs on cs.id = ccs.courierScheduleId, courierZone " +
                                "WHERE cs.courierId = courierZone.courierId " +
                                "AND courierZone.zoneId in "+ reformatZoneIds;
        return jdbcTemplate.query(scheduleQuery, BeanPropertyRowMapper.newInstance(Schedule.class));
    }

    /**
     * Insert an canceledCourierSchedule in the database, the canceledCourierSchedule is formed by the cancelRequest
     * The "approved column is always false because the request first need to exist before it can be approved"
     * @param cancelRequest consists of the schedule ID and the reason for the cancel request
     */
    public void insertCancelRequest (CancelCourierScheduleRequestDTO cancelRequest) throws Exception {
        try {
            jdbcTemplate.update("INSERT INTO canceledCourierSchedule (courierScheduleId, approved, reason) VALUES (?,?,?)",
                    cancelRequest.getCourierScheduleId(), false, cancelRequest.getReason());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
