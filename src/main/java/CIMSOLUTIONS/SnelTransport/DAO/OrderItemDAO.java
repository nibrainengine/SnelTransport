package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.class_objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemDAO {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This function fetches all orderItems of a specific Route by inserting the courierid, scheduleid and index in the query.
     * It returns a List of orderItems.
     * @param index
     * @param courierId
     * @param scheduleId
     * @return
     */
    public List<OrderItem> getOrderItems(int index, int courierId, int scheduleId) {
        try {
            String queryOrderItems = "select distinct orderItem.id as id, orderItem.quantity as quantity, orderItem.totalPrice as price, orderItem.eta as eta, orderStatus.name as orderStatus from courier, scheduleRoute, address, orderItem, orderStatus, courierSchedule where courier.userId = "+courierId+" and courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId and scheduleRoute.indexOrder = "+index+" and scheduleRoute.orderItemsId = orderItem.id and orderItem.orderStatusId = orderStatus.id";

            List<OrderItem> orderItems;
            orderItems = jdbcTemplate.query(queryOrderItems, BeanPropertyRowMapper.newInstance(OrderItem.class));
            return orderItems;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
