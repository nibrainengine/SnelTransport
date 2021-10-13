package CIMSOLUTIONS.SnelTransport.ServicesTests;

import CIMSOLUTIONS.SnelTransport.DAO.CourierAvailabilityDAO;
import CIMSOLUTIONS.SnelTransport.Services.CourierAvailabilityService;
import CIMSOLUTIONS.SnelTransport.Models.AvailablePeriod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourierAvailabilityServiceTest {

    @InjectMocks
    CourierAvailabilityService courierAvailabilityService;

    @Mock
    private CourierAvailabilityDAO courierAvailabilityDAO;

    private AvailablePeriod availablePeriod;

    @BeforeEach
    public void setup(){
        availablePeriod = getAvailablePeriod();
    }

    @Test
    void testGet() throws Exception {
        List<AvailablePeriod> availablePeriods = Collections.singletonList(availablePeriod);
        when(courierAvailabilityDAO.get(availablePeriod.getCourierId())).thenReturn(availablePeriods);
        assertEquals(availablePeriods,courierAvailabilityService.get(availablePeriod.getCourierId()));
    }

    @Test
    void testGetEmptyList() throws Exception {
        assertEquals(Collections.emptyList(),courierAvailabilityService.get(availablePeriod.getCourierId()));
    }

    @Test
    void testCreate() throws Exception {
        when(courierAvailabilityDAO.insert(availablePeriod)).thenReturn(availablePeriod.getId());
        assertEquals(availablePeriod.getId(),courierAvailabilityService.create(availablePeriod));
    }

    @Test
    void testCreateIs0() throws Exception {
        assertEquals(0,courierAvailabilityService.create(availablePeriod));
    }

    @Test
    void testApprove() throws Exception {
        when(courierAvailabilityDAO.approve(any(Integer.class))).thenReturn(availablePeriod);
        assertEquals(true, courierAvailabilityDAO.approve(1).getApproved());
    }

    private AvailablePeriod getAvailablePeriod(){
        AvailablePeriod availablePeriod = new AvailablePeriod();
        availablePeriod.setId(1);
        availablePeriod.setCourierId(1);
        availablePeriod.setStartTime(new Date());
        availablePeriod.setEndTime(new Date());
        availablePeriod.setPrice(10);
        availablePeriod.setApproved(true);
        return availablePeriod;
    }
}
