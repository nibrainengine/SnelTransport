package CIMSOLUTIONS.SnelTransport.ServicesTests;

import CIMSOLUTIONS.SnelTransport.DAO.CourierScheduleDAO;
import CIMSOLUTIONS.SnelTransport.DTO.ScheduleDTO;
import CIMSOLUTIONS.SnelTransport.Services.CourierScheduleService;
import CIMSOLUTIONS.SnelTransport.Models.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.Date;

@SpringBootTest
public class CourierScheduleServiceTest {

    @InjectMocks
    CourierScheduleService courierScheduleService;

    @Mock
    private CourierScheduleDAO courierScheduleDAO;

    private Schedule schedule;
    private ScheduleDTO scheduleDTO;

    @BeforeEach
    public void setup(){
        schedule = getSchedule();
        scheduleDTO = getScheduleDTO();
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
    void testGetCombinedSchedules() {
        List<Schedule> schedules = Collections.singletonList(schedule);
        List<ScheduleDTO> scheduleDTOS = Collections.singletonList(scheduleDTO);

        when(courierScheduleDAO.getAllSchedules()).thenReturn(schedules);
        assertEquals(scheduleDTOS.get(0).getNrScheduledCouriers(),courierScheduleService.getCombinedSchedules().get(0).getNrScheduledCouriers());
        when(courierScheduleDAO.getAllSchedules()).thenReturn(schedules);
        assertEquals(courierScheduleService.getCombinedSchedules().get(0).getClass(), ScheduleDTO.class);
    }

    @Test
    void testGetCombinedSchedulesFilteredByZones() {
        List<Schedule> schedules = Collections.singletonList(schedule);
        List<ScheduleDTO> scheduleDTOS = Collections.singletonList(scheduleDTO);
        int[] zones = {1};

        when(courierScheduleDAO.getAllSchedulesFilteredByZones(zones)).thenReturn(schedules);
        assertEquals(scheduleDTOS.get(0).getNrScheduledCouriers(),courierScheduleService.getCombinedSchedulesFilteredByZones(zones).get(0).getNrScheduledCouriers());
        when(courierScheduleDAO.getAllSchedulesFilteredByZones(zones)).thenReturn(schedules);
        assertEquals(courierScheduleService.getCombinedSchedulesFilteredByZones(zones).get(0).getClass(), ScheduleDTO.class);
    }

    private Schedule getSchedule(){
        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setStartTime(new Date());
        Date halfHourLater = new Date();
        halfHourLater.setTime(halfHourLater.getTime() + (1800 * 1000));
        schedule.setEndTime(halfHourLater);
        return schedule;
    }

    private ScheduleDTO getScheduleDTO(){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setStartTime(new Date());
        System.out.println("Date: "+scheduleDTO.getStartTime());
        Date halfHourLater = new Date();
        halfHourLater.setTime(halfHourLater.getTime() + (1800 * 1000));
        scheduleDTO.setEndTime(halfHourLater);
        scheduleDTO.setNrScheduledCouriers(1);
        return scheduleDTO;
    }
}
