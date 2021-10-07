package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.DTO.CancelCourierScheduleRequestDTO;
import CIMSOLUTIONS.SnelTransport.Enums.ScheduleStatus;
import CIMSOLUTIONS.SnelTransport.Models.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
     * @return List<CourierDTO>
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
