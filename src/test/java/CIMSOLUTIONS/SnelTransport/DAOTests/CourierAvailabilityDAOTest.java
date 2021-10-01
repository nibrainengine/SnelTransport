package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.CourierAvailabilityDAO;
import CIMSOLUTIONS.SnelTransport.class_objects.AvailablePeriod;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourierAvailabilityDAOTest {

    @Mock
    private CourierAvailabilityDAO availabilityDAO;

    @Test
    void get() throws Exception {
        List<AvailablePeriod> availablePeriods = Collections.singletonList(new AvailablePeriod());
        int courierId = 1;

        when(availabilityDAO.get(courierId)).thenReturn(availablePeriods);
        assertEquals(1, availabilityDAO.get(courierId).size());
    }

    @Test
    void insert() throws Exception {
        AvailablePeriod availablePeriod = new AvailablePeriod();
        availablePeriod.setId(1);
        availablePeriod.setCourierId(1);
        availablePeriod.setStartTime(new Date());
        availablePeriod.setEndTime(new Date());

        when(availabilityDAO.getId(availablePeriod.getCourierId(), availablePeriod.getStartTime(), availablePeriod.getEndTime())).thenReturn(availablePeriod.getId());
        when(availabilityDAO.insert(availablePeriod)).thenReturn(availablePeriod.getId());
        assertEquals(availablePeriod.getId(), availabilityDAO.insert(availablePeriod));
    }

}
