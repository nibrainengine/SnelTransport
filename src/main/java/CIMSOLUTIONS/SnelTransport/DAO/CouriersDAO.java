package CIMSOLUTIONS.SnelTransport.DAO;

import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CouriersDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Function that returns a list of couriers in dto format, ensuring only the id and name are given, by extracting
     * data both from the couriers and user tables.
     * @return List<CourierDTO>
     */
    public List<CourierDTO> getAll()  {
        String query =  "SELECT courier.userId as id, [user].userName FROM courier, [user] " +
                "WHERE [user].userId = courier.userId";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(CourierDTO.class));
    }
}