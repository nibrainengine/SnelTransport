package CIMSOLUTIONS.SnelTransport.DAO;

import class_objects.PickUpHub;
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
        String query = "SELECT url FROM pickUpHub WHERE url IS NOT NULL AND isDisabled = 'False'";
        return jdbcTemplate.queryForList(query, String.class);
    }

    public void postPickupHub(PickUpHub pickupHub){
        String query = "INSERT INTO pickUpHub VALUES(?,?,?)";
        jdbcTemplate.update(query, pickupHub.getAddress(), pickupHub.getDisabled(), pickupHub.getUrl());
    }

    public void insertDummies(){
        jdbcTemplate.update("INSERT INTO pickUpHub VALUES (?,?,?) ", 2, false, "http://localhost:8080/api/MockPickupData");
        jdbcTemplate.update("INSERT INTO pickUpHub VALUES (?,?,?) ", 3, false, "http://localhost:8080/api/MockPickupData");
        jdbcTemplate.update("INSERT INTO pickUpHub VALUES (?,?,?) ", 4, false, "http://localhost:8080/api/MockPickupData");
    }
}
