package CIMSOLUTIONS.SnelTransport.DAO;
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
        String queryRoute = "SELECT DISTINCT courierSchedule.id as id, courierSchedule.start as startTime, " +
                "courierSchedule.[end] as endTime " +
                "FROM courierSchedule " +
                "WHERE courierSchedule.courierID = "+courierId;
        return jdbcTemplate.query(queryRoute, BeanPropertyRowMapper.newInstance(Schedule.class));
    }

    /**
     * Function that returns a list of schedules all couriers.
     * @return List<Schedule>
     */
    public List<Schedule> getAllSchedules()  {
        String scheduleQuery =  "SELECT DISTINCT courierSchedule.start as startTime, courierSchedule.[end] as endTime " +
            "FROM courierSchedule";
        return jdbcTemplate.query(scheduleQuery, BeanPropertyRowMapper.newInstance(Schedule.class));
    }

    /**
     * Function that returns a list of schedules of the amount of couriers working at the specified zones.
     * @param zoneFilters - List of zone ids of the zones in which the couriers should work
     * @return List<Schedule>
     */
    public List<Schedule> getAllSchedulesFilteredByZones(int[] zoneFilters)  {
        if(zoneFilters.length <= 0){
            return new ArrayList<>();
        }
        String reformatZoneIds = Arrays.toString(zoneFilters);
        reformatZoneIds = reformatZoneIds.replace("[", "(").replace("]", ")");
        String scheduleQuery =  "SELECT DISTINCT courierSchedule.start as startTime, courierSchedule.[end] as endTime " +
                "FROM courierSchedule, courierZone " +
                "WHERE courierSchedule.courierId = courierZone.courierId " +
                "AND courierZone.zoneId in "+ reformatZoneIds;
        return jdbcTemplate.query(scheduleQuery, BeanPropertyRowMapper.newInstance(Schedule.class));
    }
}
