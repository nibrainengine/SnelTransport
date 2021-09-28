package CIMSOLUTIONS.SnelTransport.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PickuphubDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getURLS() {
        String query = "SELECT url FROM pickUpHub WHERE url IS NOT NULL";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(String.class));
    }
}
