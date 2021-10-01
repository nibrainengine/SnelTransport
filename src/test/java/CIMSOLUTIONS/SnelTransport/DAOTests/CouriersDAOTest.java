package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.CouriersDAO;
import CIMSOLUTIONS.SnelTransport.DTO.*;
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
}
