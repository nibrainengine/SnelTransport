package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.CouriersDAO;
import CIMSOLUTIONS.SnelTransport.DTO.*;
import CIMSOLUTIONS.SnelTransport.Models.Courier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Sql({"classpath:schema.sql", "classpath:test-data.sql"})
public class CouriersDAOTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Mock
    private CouriersDAO couriersDAO;

    @Mock
    private CouriersDAO couriersDAOMock;

    @BeforeEach
    void BeforeEach() {
        couriersDAOMock = new CouriersDAO();
        couriersDAOMock.setInjectedBean(this.jdbcTemplate);
    }

    @Test
    void get() {
        List<CourierDTO> courierDTOS = Collections.singletonList(new CourierDTO());

        when(couriersDAO.getAll()).thenReturn(courierDTOS);
        assertEquals(1, couriersDAO.getAll().size());
        when(couriersDAO.getAll()).thenReturn(courierDTOS);
        assertSame(couriersDAO.getAll().get(0).getClass(), CourierDTO.class);
    }

    @Test
    void getCourierInfo() throws Exception {
        Courier courier = new Courier();
        when(couriersDAO.getCourierInfo(1)).thenReturn(courier);
        assertTrue(couriersDAO.getCourierInfo(1).getClass() == Courier.class);
    }

    @Test
    void save() throws Exception {
        Courier courier = new Courier();
        when(couriersDAO.save(courier)).thenReturn(courier);
        assertTrue(couriersDAO.save(courier).getClass() == Courier.class);
    }

    @Test
    void addZoneToCourierOk(){
        try {
            CourierZoneDTO courierZoneDTO = new CourierZoneDTO();
            courierZoneDTO.setCourierId(1);
            courierZoneDTO.setZoneId(2);

            couriersDAOMock.addZoneToCourier(courierZoneDTO);
        }
        catch (Exception e) {
            fail();
        }
    }

    @Test
    void addZoneToCourierDuplicateException(){
        try {
            CourierZoneDTO courierZoneDTO = new CourierZoneDTO();
            courierZoneDTO.setCourierId(1);
            courierZoneDTO.setZoneId(1);

            couriersDAOMock.addZoneToCourier(courierZoneDTO);
            fail();
        }
        catch (DuplicateKeyException e){
            //success
        }
        catch (Exception e) {
            fail();
        }
    }
}
