package CIMSOLUTIONS.SnelTransport.ServicesTests;

import CIMSOLUTIONS.SnelTransport.DAO.CouriersDAO;
import CIMSOLUTIONS.SnelTransport.Models.Courier;
import CIMSOLUTIONS.SnelTransport.Models.Zone;
import CIMSOLUTIONS.SnelTransport.Services.CouriersService;
import CIMSOLUTIONS.SnelTransport.DTO.*;
import CIMSOLUTIONS.SnelTransport.Services.RouteService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CouriersServiceTest {

    @InjectMocks
    CouriersService couriersService;

    CouriersService mockService = Mockito.mock(CouriersService.class);

    @Mock
    private CouriersDAO couriersDAO;

    private CourierDTO courierDTO;

    private Courier courier;

    @BeforeEach
    public void setup(){
        courierDTO = getCourierDTO();
    }

    @BeforeEach
    public void setupCourier(){courier = getCourier();}

    @Test
    void testGet() {
        List<CourierDTO> courierDTOS = Collections.singletonList(courierDTO);
        when(couriersDAO.getAll()).thenReturn(courierDTOS);
        assertEquals(courierDTOS,couriersDAO.getAll());
        when(mockService.getAll()).thenReturn(courierDTOS);
        assertSame(mockService.getAll().get(0).getClass(), CourierDTO.class);
    }

    @Test
    void testGetCourier() throws Exception {
        when(mockService.getCourierInfo(1)).thenReturn(courier);
        assertSame(mockService.getCourierInfo(1).getClass(), Courier.class);
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

    @Test
    void addZoneToCourierSuccess() {
        try {
            CourierZoneDTO courierZoneDTO = new CourierZoneDTO();
            courierZoneDTO.setZoneId(1);
            courierZoneDTO.setCourierId(1);

            doNothing().when(couriersDAO).addZoneToCourier(courierZoneDTO);
            couriersService.addZoneToCourier(courierZoneDTO);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void addZoneToCourierException() {
        try {
            CourierZoneDTO courierZoneDTO = new CourierZoneDTO();
            courierZoneDTO.setZoneId(1);
            courierZoneDTO.setCourierId(1);

            doThrow(new Exception()).when(couriersDAO).addZoneToCourier(courierZoneDTO);
            couriersService.addZoneToCourier(courierZoneDTO);
            fail();
        } catch (Exception e) {
            //success
        }
    }

    private Courier getCourier(){
        Courier courier = new Courier();
        courier.setId(1);
        courier.setKvkNumber(11111111);
        return courier;
    }
}
