package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.PickUpHubDAO;
import CIMSOLUTIONS.SnelTransport.DTO.PickUpAPIDTO;
import CIMSOLUTIONS.SnelTransport.Models.Address;
import CIMSOLUTIONS.SnelTransport.Models.PickUpHub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@JdbcTest
@Sql({"classpath:schema.sql", "classpath:test-data.sql"})
public class PickupDAOTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private PickUpHubDAO pickUpHubDAO;

    @BeforeEach
    void BeforeEach(){
        pickUpHubDAO = new PickUpHubDAO();
        pickUpHubDAO.setInjectedBean(this.jdbcTemplate);
    }

    @Test
    void getPickupData_shouldReturnAllEnabledPickups(){
            List<PickUpHub> hubs = pickUpHubDAO.getURLsandAddresses();
            assertEquals(2, hubs.size());
    }

    @Test
    void getPickupAddress(){
        List<PickUpHub> hubs = pickUpHubDAO.getURLsandAddresses();
        assertEquals("Teststraat1", hubs.get(0).getAddress().getStreet());
    }

    @Test
    void postPickUp(){
        PickUpHub newPickupHub = new PickUpHub( new Address("TestingStreet", "12a", "4111xd", "Amersfoort", "The Netherlands", 12.5, 13.5), false, "http://fakeurlxd.com");
        try{
            pickUpHubDAO.postPickupHub(newPickupHub);
            List<PickUpHub> hubs = pickUpHubDAO.getURLsandAddresses();
            assertEquals(newPickupHub.getAddress().getStreet(), hubs.get(2).getAddress().getStreet());
            assertEquals(newPickupHub.getUrl(), hubs.get(2).getUrl());
        }

        catch(Exception e){
            fail();
        }

    }

    @Test
    void getAPIs(){
        List<PickUpAPIDTO> dtos = pickUpHubDAO.getAPIs();
        assertEquals(3, dtos.size());
        assertEquals(true, dtos.get(1).isDisabled());
    }

    @Test
    void updateStatus(){
        PickUpHub changedHub = pickUpHubDAO.enableDisablePickup(1);
        assertEquals(true, changedHub.getDisabled());
    }
}
