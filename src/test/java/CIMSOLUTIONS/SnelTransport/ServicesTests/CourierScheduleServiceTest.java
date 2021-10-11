package CIMSOLUTIONS.SnelTransport.ServicesTests;

import CIMSOLUTIONS.SnelTransport.DAO.CourierScheduleDAO;
import CIMSOLUTIONS.SnelTransport.DTO.CancelCourierScheduleRequestDTO;
import CIMSOLUTIONS.SnelTransport.DTO.ScheduleDTO;
import CIMSOLUTIONS.SnelTransport.Enums.ScheduleStatus;
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
    private ScheduleDTO scheduleDTO;

    @BeforeEach
    public void setup(){
        schedule = getSchedule();
        scheduleDTO = getScheduleDTO();
    }

    @Test
    void testGetScheduled() {
        schedule.setScheduleStatus(ScheduleStatus.Scheduled.name());
        List<Schedule> schedules = new java.util.ArrayList<>(Collections.singletonList(schedule));
        when(courierScheduleDAO.get(schedule.getId())).thenReturn(schedules);
        assertSame(schedules,courierScheduleService.getScheduled(schedule.getId()));
    }

    @Test
    void testGetScheduledEmptyListWhenCancelled() {
        schedule.setScheduleStatus(ScheduleStatus.Cancelled.name());
        List<Schedule> schedules = new java.util.ArrayList<>(Collections.singletonList(schedule));
        when(courierScheduleDAO.get(schedule.getId())).thenReturn(schedules);
        assertSame(0,courierScheduleService.getScheduled(schedule.getId()).size());
    }

    @Test
    void testGetEmptyList() {
        assertEquals(Collections.emptyList(),courierScheduleService.getScheduled(schedule.getId()));
    }

    @Test
    void testGetCombinedSchedules() {
        List<Schedule> schedules = new java.util.ArrayList<>(Collections.singletonList(schedule));
        List<ScheduleDTO> scheduleDTOS = Collections.singletonList(scheduleDTO);

        when(courierScheduleDAO.getAllSchedules()).thenReturn(schedules);
        assertEquals(scheduleDTOS.get(0).getNrScheduledCouriers(),courierScheduleService.getCombinedSchedules().get(0).getNrScheduledCouriers());
        when(courierScheduleDAO.getAllSchedules()).thenReturn(schedules);
        assertEquals(courierScheduleService.getCombinedSchedules().get(0).getClass(), ScheduleDTO.class);
    }

    @Test
    void testGetCombinedSchedulesFilteredByZones() {
        List<Schedule> schedules = new java.util.ArrayList<>(Collections.singletonList(schedule));
        List<ScheduleDTO> scheduleDTOS = Collections.singletonList(scheduleDTO);
        int[] zones = {1};

        when(courierScheduleDAO.getAllSchedulesFilteredByZones(zones)).thenReturn(schedules);
        assertEquals(scheduleDTOS.get(0).getNrScheduledCouriers(),courierScheduleService.getCombinedSchedulesFilteredByZones(zones).get(0).getNrScheduledCouriers());
        when(courierScheduleDAO.getAllSchedulesFilteredByZones(zones)).thenReturn(schedules);
        assertEquals(courierScheduleService.getCombinedSchedulesFilteredByZones(zones).get(0).getClass(), ScheduleDTO.class);
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
        Date halfHourLater = new Date();
        halfHourLater.setTime(halfHourLater.getTime() + (1800 * 1000));
        schedule.setEndTime(halfHourLater);
        schedule.setScheduleStatus(ScheduleStatus.Scheduled.name());
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
