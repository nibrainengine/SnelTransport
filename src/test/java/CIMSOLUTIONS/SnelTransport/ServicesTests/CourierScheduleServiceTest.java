package CIMSOLUTIONS.SnelTransport.ServicesTests;

import CIMSOLUTIONS.SnelTransport.DAO.CourierScheduleDAO;
import CIMSOLUTIONS.SnelTransport.Services.CourierScheduleService;
import CIMSOLUTIONS.SnelTransport.class_objects.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.Date;

@SpringBootTest
public class CourierScheduleServiceTest {

    @InjectMocks
    CourierScheduleService courierScheduleService;

    @Mock
    private CourierScheduleDAO courierScheduleDAO;

    private Schedule schedule;

    @BeforeEach
    public void setup(){
        schedule = getSchedule();
    }

    @Test
    void testGet() {
        List<Schedule> schedules = Collections.singletonList(schedule);
        when(courierScheduleDAO.get(schedule.getId())).thenReturn(schedules);
        assertEquals(schedules,courierScheduleService.get(schedule.getId()));
        when(courierScheduleService.get(0)).thenReturn(schedules);
        assertTrue(courierScheduleService.get(0).get(0).getClass() == Schedule.class);
    }

    @Test
    void testGetEmptyList() {
        assertEquals(Collections.emptyList(),courierScheduleService.get(schedule.getId()));
    }

    private Schedule getSchedule(){
        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setStartTime(new Date());
        schedule.setEndTime(new Date());
        return schedule;
    }
}
