package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.class_objects.Schedule;
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
        String queryRoute = "SELECT DISTINCT courierSchedule.id as id, courierSchedule.start as startTime, " +
                "courierSchedule.[end] as endTime " +
                "FROM courierSchedule " +
                "WHERE courierSchedule.courierID = "+courierId;
        return jdbcTemplate.query(queryRoute, BeanPropertyRowMapper.newInstance(Schedule.class));
    }
}
