package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.class_objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressDAO {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Address getStartAddress(int courierId, int scheduleId){
        try {
            String queryStartAddress = "select distinct address.id as id, address.street as street, address.houseNumber as housenumber, address.zipCode as zipcode, address.city as city, address.country as country, address.longitude as longitude ,address.latitude as latitude from courier, courierSchedule, scheduleRoute, address where courier.userId = "+courierId+" and courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.startAddress = address.id";

            Address address;
            address = jdbcTemplate.query(queryStartAddress, BeanPropertyRowMapper.newInstance(Address.class)).get(0);
            return address;

        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public Address getEndAddress(int courierId, int scheduleId){
        try {
            String queryEndAddress = "select distinct address.id as id, address.street as street, address.houseNumber as housenumber, address.zipCode as zipcode, address.city as city, address.country as country, address.longitude as longitude ,address.latitude as latitude from courier, courierSchedule, scheduleRoute, address where courier.userId = "+courierId+" and courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.endAddress = address.id";

            Address address;
            address = jdbcTemplate.query(queryEndAddress, BeanPropertyRowMapper.newInstance(Address.class)).get(0);
            return address;

        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public Address getDeliveryAddress(int index, int courierId, int scheduleId){
        try {
            String queryDeliveryAddress = "select distinct address.id as id, address.street as street, address.houseNumber as housenumber, address.zipCode as zipcode, address.city as city, address.country as country, address.longitude as longitude ,address.latitude as latitude  from courier, scheduleRoute, address, orderItem, orderStatus, courierSchedule, product, productSize, category, productCategory where courier.userId = "+courierId+" and courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId and scheduleRoute.indexOrder = "+index+" and scheduleRoute.orderItemsId = orderItem.id and orderItem.deliveryAddressId = address.id";

            Address address;
            address = jdbcTemplate.query(queryDeliveryAddress, BeanPropertyRowMapper.newInstance(Address.class)).get(0);
            return address;

        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }


}
