package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.CourierScheduleDAO;
import CIMSOLUTIONS.SnelTransport.Models.Schedule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourierScheduleDAOTest {

    @Mock
    private CourierScheduleDAO courierScheduleDAO;

    @Test
    void get() {
        List<Schedule> schedules = Collections.singletonList(new Schedule());
        int courierId = 1;

        when(courierScheduleDAO.get(courierId)).thenReturn(schedules);
        assertEquals(1, courierScheduleDAO.get(courierId).size());
        when(courierScheduleDAO.get(courierId)).thenReturn(schedules);
        assertSame(courierScheduleDAO.get(courierId).get(0).getClass(), Schedule.class);
    }
}
