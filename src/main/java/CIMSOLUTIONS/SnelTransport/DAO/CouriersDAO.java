package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.Models.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import CIMSOLUTIONS.SnelTransport.DTO.*;
import java.util.List;

@Component
public class CouriersDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Function that returns a list of couriers in CIMSOLUTIONS.SnelTransport.dto format, ensuring only the id and name
     * are given, by extracting data both from the couriers and user tables.
     * @return List<CourierDTO>
     */
    public List<CourierDTO> getAll()  {
        String query =  "SELECT courier.userId as id, [user].fullName FROM courier, [user] " +
                "WHERE [user].userId = courier.userId";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(CourierDTO.class));
    }

    /**
     * Function that returns a single Courier with the id and kvknumber fetched by the courierid.
     * @param courierId unique identification number of a courier.
     * @return single Courier object
     * @throws Exception Exemption message.
     */
    public Courier getCourierInfo(int courierId) throws Exception {
        try {
            String query = "select courier.userId as id, courier.kvkNumber as kvkNumber" +
                    " from courier where userId = " + courierId;
            return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Courier.class)).get(0);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Adds zone to courier
     * @param courierZoneDTO object with courierId and zoneId
     * @throws Exception Exception on insert
     */
    public void addZoneToCourier(CourierZoneDTO courierZoneDTO) throws Exception{
        try{
            String query = "INSERT INTO courierZone (courierId, zoneId, isApproved) " +
                    "VALUES ('"+ courierZoneDTO.getCourierId() +"', '"+ courierZoneDTO.getZoneId() +"', 'false')";
            jdbcTemplate.update(query);
        }
        catch(DuplicateKeyException e){
            throw new DuplicateKeyException(e.getMessage());
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
