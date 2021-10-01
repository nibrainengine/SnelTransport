package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AddressDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This function fetches the address row of an route with the courierid and scheduleid filled in. It returns an Address object.
     * @param courierId
     * @param scheduleId
     * @return
     */
    public Address getStartAddress(int courierId, int scheduleId) throws Exception {
        try {
            String queryStartAddress = "select distinct address.id as id, address.street as street, address.houseNumber as housenumber, address.zipCode as zipcode, address.city as city, address.country as country, address.longitude as longitude ,address.latitude as latitude from courier, courierSchedule, scheduleRoute, address where courier.userId = "+courierId+" and courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId and courierSchedule.startAddress = address.id";
            Address address;
            address = jdbcTemplate.query(queryStartAddress, BeanPropertyRowMapper.newInstance(Address.class)).get(0);
            return address;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This function fetches the address row of an route with the courierid and scheduleid filled in. It returns an Address object.
     * The differance between this function and the getStartAddress() function is that courierSchedule.endAddress = address.id instead of
     * courierSchedule.startAddress = address.id
     * @param courierId
     * @param scheduleId
     * @return
     */
    public Address getEndAddress(int courierId, int scheduleId) throws Exception {
        try {
            String queryEndAddress = "select distinct address.id as id, address.street as street, address.houseNumber as housenumber, address.zipCode as zipcode, address.city as city, address.country as country, address.longitude as longitude ,address.latitude as latitude from courier, courierSchedule, scheduleRoute, address where courier.userId = "+courierId+" and courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId and courierSchedule.endAddress = address.id";

            Address address;
            address = jdbcTemplate.query(queryEndAddress, BeanPropertyRowMapper.newInstance(Address.class)).get(0);
            return address;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This function fetches the address row of an route with the courierid, scheduleid and index filled in. It returns an Address object.
     * The check on adress.id is now: orderItem.deliveryAddressId = address.id. This function also needs an index parameter so the query can
     * fetch the orderItems of a specific Route.
     * @param index
     * @param courierId
     * @param scheduleId
     * @return
     */
    public Address getDeliveryAddress(int index, int courierId, int scheduleId) throws Exception {
        try {
            String queryDeliveryAddress = "select distinct address.id as id, address.street as street, address.houseNumber as housenumber, address.zipCode as zipcode, address.city as city, address.country as country, address.longitude as longitude ,address.latitude as latitude  from courier, scheduleRoute, address, orderItem, orderStatus, courierSchedule, product, productSize, category, productCategory where courier.userId = "+courierId+" and courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId and courierSchedule.id = scheduleRoute.courierScheduleId and scheduleRoute.indexOrder = "+index+" and scheduleRoute.orderItemsId = orderItem.id and orderItem.deliveryAddressId = address.id";

            Address address;
            address = jdbcTemplate.query(queryDeliveryAddress, BeanPropertyRowMapper.newInstance(Address.class)).get(0);
            return address;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This function fetches the address row of an route with the date filled in. It returns an Address object.
     * @param scheduleId
     * @return
     */
    public Address getStartAddressWithDate(int scheduleId) throws Exception {
        try {
            String queryStartAddress = "select distinct address.id as id, address.street as street, address.houseNumber as housenumber, address.zipCode as zipcode, address.city as city, address.country as country, address.longitude as longitude ,address.latitude as latitude from courier, courierSchedule, scheduleRoute, address where courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId and courierSchedule.startAddress = address.id";

            Address address;
            address = jdbcTemplate.query(queryStartAddress, BeanPropertyRowMapper.newInstance(Address.class)).get(0);
            return address;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This function fetches the address row of an route with the date filled in. It returns an Address object.
     * The differance between this function and the getStartAddress() function is that courierSchedule.endAddress = address.id instead of
     * courierSchedule.startAddress = address.id
     * @param scheduleId
     * @return
     */
    public Address getEndAddressWithDate(int scheduleId) throws Exception {
        try {
            String queryEndAddress = "select distinct address.id as id, address.street as street, address.houseNumber as housenumber, address.zipCode as zipcode, address.city as city, address.country as country, address.longitude as longitude ,address.latitude as latitude from courier, courierSchedule, scheduleRoute, address where courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId and courierSchedule.endAddress = address.id";

            Address address;
            address = jdbcTemplate.query(queryEndAddress, BeanPropertyRowMapper.newInstance(Address.class)).get(0);
            return address;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This function fetches the address row of an route with the date and index filled in. It returns an Address object.
     * The check on adress.id is now: orderItem.deliveryAddressId = address.id. This function also needs an index parameter so the query can
     * fetch the orderItems of a specific Route.
     * @param index
     * @param scheduleId
     * @return
     */
    public Address getDeliveryAddressWithDate(int index, int scheduleId) throws Exception {
        try {
            String queryDeliveryAddress = "select distinct address.id as id, address.street as street, address.houseNumber as housenumber, address.zipCode as zipcode, address.city as city, address.country as country, address.longitude as longitude ,address.latitude as latitude  from courier, scheduleRoute, address, orderItem, orderStatus, courierSchedule, product, productSize, category, productCategory where courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId and courierSchedule.id = scheduleRoute.courierScheduleId and scheduleRoute.indexOrder = "+index+" and scheduleRoute.orderItemsId = orderItem.id and orderItem.deliveryAddressId = address.id";

            Address address;
            address = jdbcTemplate.query(queryDeliveryAddress, BeanPropertyRowMapper.newInstance(Address.class)).get(0);
            return address;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
