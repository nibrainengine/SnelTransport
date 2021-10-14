package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.CouriersDAO;
import CIMSOLUTIONS.SnelTransport.DTO.*;
import CIMSOLUTIONS.SnelTransport.Models.Courier;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CouriersDAOTest {

    @Mock
    private CouriersDAO couriersDAO;

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
}
