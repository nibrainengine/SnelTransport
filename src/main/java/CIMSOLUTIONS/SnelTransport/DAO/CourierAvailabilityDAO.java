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

    public List<AvailablePeriod> get(int courierId)  {
        String query =  "SELECT id, start as startTime, [end] as endTime, price, isApproved " +
                        "FROM courierAvailablePeriod " +
                        "WHERE courierId = " + courierId;
        List<AvailablePeriod> availablePeriods;
        availablePeriods = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(AvailablePeriod.class));
        return availablePeriods;
    }

    public int insert(AvailablePeriod availablePeriod) {
        if(!courierAvailablePeriodExists(availablePeriod.getCourierId(), availablePeriod.getStartTime(), availablePeriod.getEndTime())) {
            jdbcTemplate.update("INSERT INTO courierAvailablePeriod (courierId, start, [end], price, isApproved) VALUES (?,?,?,?,?)",
                    availablePeriod.getCourierId(), availablePeriod.getStartTime(), availablePeriod.getEndTime(), availablePeriod.getPrice(), availablePeriod.getApproved());
        }
        return getId(availablePeriod.getCourierId(), availablePeriod.getStartTime(), availablePeriod.getEndTime());
    }

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

    private boolean courierAvailablePeriodExists(int courierId, Date startTime, Date endTime){
        return getId(courierId, startTime, endTime) != 0;
    }

}
