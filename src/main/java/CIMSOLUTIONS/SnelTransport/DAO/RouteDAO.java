package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteDAO {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This function returns a list of routes for a specific courier on a specific day/schedule by inserting a courierid and scheduleid in the query.
     * @param courierId
     * @param scheduleId
     * @return
     */
    public List<Route> get(int courierId, int scheduleId) throws Exception {
        try {
            String queryRoute = "select distinct scheduleRoute.courierScheduleId as id, scheduleRoute.indexOrder as [index], scheduleRoute.startTime as startTime, scheduleRoute.endTime as endTime from courier, courierSchedule, scheduleRoute where courier.userId = " + courierId + " and courierSchedule.id = " + scheduleId + " and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId";
            List<Route> routes;
            routes = jdbcTemplate.query(queryRoute, BeanPropertyRowMapper.newInstance(Route.class));
            System.out.println(routes.get(0).getId());
            return routes;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This function returns a list of routes for a specific courier on a specific day/schedule by inserting a date in the query.
     * @param date
     * @return
     */
    public List<Route> getWithDate(String date) throws Exception {
        try {
            String queryRoute = "select distinct scheduleRoute.courierScheduleId as id, scheduleRoute.indexOrder as [index], scheduleRoute.startTime as startTime, scheduleRoute.endTime as endTime from scheduleRoute where CONVERT(VARCHAR(25), scheduleRoute.startTime, 126) LIKE '%"+date+"%'";
            List<Route> routes;
            routes = jdbcTemplate.query(queryRoute, BeanPropertyRowMapper.newInstance(Route.class));
            return routes;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
