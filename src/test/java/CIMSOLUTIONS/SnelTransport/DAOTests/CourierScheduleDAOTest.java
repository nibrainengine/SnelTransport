package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.CourierScheduleDAO;
import CIMSOLUTIONS.SnelTransport.DTO.CancelCourierScheduleRequestDTO;
import CIMSOLUTIONS.SnelTransport.Models.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@JdbcTest
@Sql({"classpath:schema.sql", "classpath:test-data.sql"})
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

    @Test
    void getAllSchedules() {
        List<Schedule> schedules = Collections.singletonList(new Schedule());

        when(courierScheduleDAO.getAllSchedules()).thenReturn(schedules);
        assertEquals(1, courierScheduleDAO.getAllSchedules().size());
        assertSame(courierScheduleDAO.getAllSchedules().get(0).getClass(), Schedule.class);
    }

    @Test
    void getAllSchedulesFilteredByZones() {
        List<Schedule> schedules = Collections.singletonList(new Schedule());
        int[] zones = {1};

        when(courierScheduleDAO.getAllSchedulesFilteredByZones(zones)).thenReturn(schedules);
        assertEquals(1, courierScheduleDAO.getAllSchedulesFilteredByZones(zones).size());
        assertSame(courierScheduleDAO.getAllSchedulesFilteredByZones(zones).get(0).getClass(), Schedule.class);
    }

    @Test
    void testInsertCancelRequest() {
        try {
            CancelCourierScheduleRequestDTO cancelRequest = new CancelCourierScheduleRequestDTO();
            cancelRequest.setCourierScheduleId(1);
            cancelRequest.setReason("reason");
            courierScheduleDAO.insertCancelRequest(cancelRequest);
            Assertions.assertDoesNotThrow(() -> courierScheduleDAO.insertCancelRequest(cancelRequest));
        }
        catch (Exception e) {
            fail();
        }
    }
}
