package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.Models.AvailablePeriod;
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
     * @param courierId identification of a courier
     * @return List<AvailablePeriod> AvailablePeriod contains data on when a courier is available
     */
    public List<AvailablePeriod> get(int courierId) throws Exception {
        //TODO: zorg ervoor dat booleans als anders dan false ingelezen kunnen worden
        String query =  "SELECT id, start as startTime, [end] as endTime, price, isApproved " +
                        "FROM courierAvailablePeriod " +
                        "WHERE courierId = " + courierId;
        List<AvailablePeriod> availablePeriods;

        try {
            availablePeriods = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(AvailablePeriod.class));
            return availablePeriods;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Insert new available period of a courier in the database
     * @param availablePeriod contains data on when a courier is available
     * @return int (id of new availablePeriod in database)
     */
    public int insert(AvailablePeriod availablePeriod) throws Exception {
        int id = 0;
        try {
            if (!courierAvailablePeriodExists(availablePeriod.getCourierId(), availablePeriod.getStartTime(), availablePeriod.getEndTime())) {
                jdbcTemplate.update("INSERT INTO courierAvailablePeriod (courierId, start, [end], price, isApproved) VALUES (?,?,?,?,?)",
                        availablePeriod.getCourierId(), availablePeriod.getStartTime(), availablePeriod.getEndTime(), availablePeriod.getPrice(), availablePeriod.getApproved());
                id = getId(availablePeriod.getCourierId(), availablePeriod.getStartTime(), availablePeriod.getEndTime());
            }
            return id;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Put call that approves or denies an available period
     * @throws Exception if the period to be changed does not exist.
     * @param id primary of the id that will be changed
     * @return the period that has just been changed.
     */
    public AvailablePeriod approve(int id) throws Exception{
        String query = "UPDATE courierAvailablePeriod SET isApproved = 1 - isApproved WHERE id=?";
        jdbcTemplate.update(query, id);
        return getOnePeriod(id);
    }

    /**
     * Returns one item from the available period table
     * @param periodId the primary key of the item in question.
     * @return AvailablePeriod object
     * @throws Exception In case the id does not exist.
     */
    public AvailablePeriod getOnePeriod(int periodId) throws Exception {
        String query =  "SELECT id, [start], [end], price, isApproved, courierId " +
                "FROM courierAvailablePeriod " +
                "WHERE id = " + periodId;
        List<AvailablePeriod> availablePeriods;

        try {
            availablePeriods = jdbcTemplate.query(query, (resultSet, i) -> {
                AvailablePeriod period = new AvailablePeriod();
                period.setId(resultSet.getInt(1));
                period.setStartTime(resultSet.getTime(2));
                period.setEndTime(resultSet.getTime(3));
                period.setPrice(resultSet.getDouble(4));
                period.setApproved(resultSet.getBoolean(5));
                period.setCourierId(resultSet.getInt(6));
                return period;
            });
            return availablePeriods.get(0);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Gets available period id from database
     * @param courierId identification of a courier
     * @param startTime the start date and time of an available period of a courier
     * @param endTime the end date and time of an available period of a courier
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
     * @param courierId identification of a courier
     * @param startTime the start date and time of an available period of a courier
     * @param endTime the end date and time of an available period of a courier
     * @return boolean (true when exists, false when not exists)
     */
    private boolean courierAvailablePeriodExists(int courierId, Date startTime, Date endTime){
        return getId(courierId, startTime, endTime) != 0;
    }

}
