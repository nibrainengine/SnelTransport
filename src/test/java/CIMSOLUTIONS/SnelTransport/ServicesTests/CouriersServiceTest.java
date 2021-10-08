package CIMSOLUTIONS.SnelTransport.ServicesTests;

import CIMSOLUTIONS.SnelTransport.DAO.CouriersDAO;
import CIMSOLUTIONS.SnelTransport.Services.CouriersService;
import CIMSOLUTIONS.SnelTransport.DTO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CouriersServiceTest {

    @InjectMocks
    CouriersService couriersService;

    @Mock
    private CouriersDAO couriersDAO;

    private CourierDTO courierDTO;

    @BeforeEach
    public void setup(){
        courierDTO = getCourierDTO();
    }

    @Test
    void testGet() {
        List<CourierDTO> courierDTOS = Collections.singletonList(courierDTO);
        when(couriersDAO.getAll()).thenReturn(courierDTOS);
        assertEquals(courierDTOS,couriersService.getAll());
        when(couriersService.getAll()).thenReturn(courierDTOS);
        assertSame(couriersService.getAll().get(0).getClass(), CourierDTO.class);
    }

    @Test
    void testGetEmptyList() {
        assertEquals(Collections.emptyList(),couriersService.getAll());
    }

    private CourierDTO getCourierDTO(){
        CourierDTO courierDTO = new CourierDTO();
        courierDTO.setId(1);
        courierDTO.setFullName("Courier 01");
        return courierDTO;
    }
}
