package CIMSOLUTIONS.SnelTransport.DAO;

import class_objects.AvailablePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class CourierAvailabilityDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Gets all available periods from a courier
     * @return List<AvailablePeriod>
     */
    public List<AvailablePeriod> get(int courierId)  {
        String query =  "SELECT id, start as startTime, [end] as endTime, price, isApproved " +
                        "FROM courierAvailablePeriod " +
                        "WHERE courierId = " + courierId;
        List<AvailablePeriod> availablePeriods;
        availablePeriods = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(AvailablePeriod.class));
        return availablePeriods;
    }

    /**
     * Insert new available period of a courier in the database
     * @return int (id of new availablePeriod in database)
     */
    public int insert(AvailablePeriod availablePeriod) {
        int id = 0;
        if(!courierAvailablePeriodExists(availablePeriod.getCourierId(), availablePeriod.getStartTime(), availablePeriod.getEndTime())) {
            jdbcTemplate.update("INSERT INTO courierAvailablePeriod (courierId, start, [end], price, isApproved) VALUES (?,?,?,?,?)",
                    availablePeriod.getCourierId(), availablePeriod.getStartTime(), availablePeriod.getEndTime(), availablePeriod.getPrice(), availablePeriod.getApproved());
            id = getId(availablePeriod.getCourierId(), availablePeriod.getStartTime(), availablePeriod.getEndTime());
        }
        return id;
    }

    /**
     * Gets available period id from database
     * @return int (id of availablePeriod in database)
     */
    public int getId(int courierId, Date startTime, Date endTime) {
        String query =  "SELECT DISTINCT id " +
                        "FROM courierAvailablePeriod " +
                        "WHERE courierId = " + courierId +
                        " AND start = '" + new Timestamp(startTime.getTime()) +
                        "' AND [end] = '" + new Timestamp(endTime.getTime()) + "'";
        try {
            return jdbcTemplate.queryForObject(query, Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Checks if available period of the courier already exists in the database
     * @return boolean (true when exists, false when not exists)
     */
    private boolean courierAvailablePeriodExists(int courierId, Date startTime, Date endTime){
        return getId(courierId, startTime, endTime) != 0;
    }
}
