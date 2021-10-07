package CIMSOLUTIONS.SnelTransport.ServicesTests;

import CIMSOLUTIONS.SnelTransport.DAO.CourierScheduleDAO;
import CIMSOLUTIONS.SnelTransport.DTO.CancelCourierScheduleRequestDTO;
import CIMSOLUTIONS.SnelTransport.Services.CourierScheduleService;
import CIMSOLUTIONS.SnelTransport.Models.Schedule;
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
        assertSame(courierScheduleService.get(0).get(0).getClass(), Schedule.class);
    }

    @Test
    void testGetEmptyList() {
        assertEquals(Collections.emptyList(),courierScheduleService.get(schedule.getId()));
    }

    @Test
    void testInsertCancelRequest() throws Exception {
        CancelCourierScheduleRequestDTO cancelRequest = new CancelCourierScheduleRequestDTO();
        doNothing().when(courierScheduleDAO).insertCancelRequest(cancelRequest);
        courierScheduleService.insertCancelRequest(cancelRequest);
        Mockito.verify(courierScheduleDAO, times(1)).insertCancelRequest(cancelRequest);
    }

    private Schedule getSchedule(){
        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setStartTime(new Date());
        schedule.setEndTime(new Date());
        return schedule;
    }
}
