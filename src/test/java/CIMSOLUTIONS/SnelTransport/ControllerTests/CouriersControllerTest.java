package CIMSOLUTIONS.SnelTransport.ControllerTests;

import CIMSOLUTIONS.SnelTransport.Models.Schedule;
import CIMSOLUTIONS.SnelTransport.Services.CourierScheduleService;
import CIMSOLUTIONS.SnelTransport.Models.Courier;
import CIMSOLUTIONS.SnelTransport.Services.CouriersService;
import CIMSOLUTIONS.SnelTransport.DTO.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CouriersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CouriersService couriersService;
    @MockBean
    private CourierScheduleService courierScheduleService;


    private CourierDTO courierDTO;
    private Schedule schedule;
    private ScheduleDTO scheduleDTO;

    private Courier courier;

    @BeforeEach
    public void setup(){
        courierDTO = getCourierDTO();
        schedule = getSchedule();
        scheduleDTO = getScheduleDTO();
    }

    @BeforeEach
    public void setupCourier(){courier = getCourier();}

    @Test
    void getAllRoutesCourier_ReturnOk() {
        try{
            when(couriersService.getCourierInfo(1)).thenReturn(this.courier);
            this.mockMvc.perform(get("/api/couriers/my-info/1")).andExpect(status().isOk());
        }
        catch (Exception ex){
            fail();
        }
    }

    @Test
    void getAllRoutesCourier_ReturnBadRequest() {
        try{
            when(couriersService.getCourierInfo(1)).thenThrow(new Exception());
            this.mockMvc.perform(get("/api/couriers/my-info/1")).andExpect(status().isBadRequest());
        }
        catch (Exception ex){
            fail();
        }
    }

    @Test
    public void testGetAll() throws Exception {
        List<CourierDTO> courierDTOS = Collections.singletonList(courierDTO);
        when(couriersService.getAll()).thenReturn(courierDTOS);

        this.mockMvc.perform(get("/api/couriers/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(courierDTO.getId()))
                .andExpect(jsonPath("$[0].fullName").value(courierDTO.getFullName()));
    }

    @Test
    public void testGetSchedule() throws Exception {
        List<Schedule> schedules = Collections.singletonList(schedule);
        when(courierScheduleService.getScheduled(1)).thenReturn(schedules);
        when(courierScheduleService.getScheduled(schedule.getId())).thenReturn(schedules);
        SimpleDateFormat sdf = getSimpleDateFormat();

        this.mockMvc.perform(get("/api/couriers/"+ 1 + "/schedule/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(schedule.getId()))
                .andExpect(jsonPath("$[0].startTime").value(sdf.format(schedule.getStartTime())))
                .andExpect(jsonPath("$[0].endTime").value(sdf.format(schedule.getEndTime())));
    }

    @Test
    public void testGetCombinedSchedules() throws Exception {
        List<ScheduleDTO> scheduleDTOS = Collections.singletonList(scheduleDTO);
        when(courierScheduleService.getCombinedSchedules()).thenReturn(scheduleDTOS);
        SimpleDateFormat sdf = getSimpleDateFormat();

        this.mockMvc.perform(get("/api/couriers/combined-schedules/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].startTime").value(sdf.format(scheduleDTO.getStartTime())))
                .andExpect(jsonPath("$[0].endTime").value(sdf.format(scheduleDTO.getEndTime())))
                .andExpect(jsonPath("$[0].nrScheduledCouriers").value(scheduleDTO.getNrScheduledCouriers()));
    }

    @Test
    public void testGetCombinedSchedulesFilteredByZones() throws Exception {
        List<ScheduleDTO> scheduleDTOS = Collections.singletonList(scheduleDTO);
        int[] zones = {1};
        when(courierScheduleService.getCombinedSchedulesFilteredByZones(zones)).thenReturn(scheduleDTOS);
        SimpleDateFormat sdf = getSimpleDateFormat();

        this.mockMvc.perform(get("/api/couriers/combined-schedules/filter/?zoneFilters="+zones[0]))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].startTime").value(sdf.format(scheduleDTO.getStartTime())))
                .andExpect(jsonPath("$[0].endTime").value(sdf.format(scheduleDTO.getEndTime())))
                .andExpect(jsonPath("$[0].nrScheduledCouriers").value(scheduleDTO.getNrScheduledCouriers()));
    }

    @Test
    public void testPost() throws Exception {
        CancelCourierScheduleRequestDTO cancelRequest = new CancelCourierScheduleRequestDTO();
        doNothing().when(courierScheduleService).insertCancelRequest(cancelRequest);
        this.mockMvc.perform(post("/api/couriers/cancel-schedule/")
                        .contentType("application/json").content(objectMapper.writeValueAsString(cancelRequest)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetBadRequest() throws Exception {
        CancelCourierScheduleRequestDTO cancelRequest = new CancelCourierScheduleRequestDTO();
        doThrow(new Exception()).when(courierScheduleService).insertCancelRequest(any(CancelCourierScheduleRequestDTO.class));
        this.mockMvc.perform(post("/api/couriers/cancel-schedule/")
                        .contentType("application/json").content(objectMapper.writeValueAsString(cancelRequest)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testPostBadRequestWrongBody() throws Exception {
        doNothing().when(courierScheduleService).insertCancelRequest(any(CancelCourierScheduleRequestDTO.class));
        this.mockMvc.perform(post("/api/couriers/cancel-schedule/")
                        .contentType("application/json").content(objectMapper.writeValueAsString(1)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void postCourierPackageSize_ReturnOk() {
        try{
            when(couriersService.save(courier)).thenReturn(courier);
            this.mockMvc.perform(post("/api/couriers/change-packagesize").contentType("application/json").content(objectMapper.writeValueAsString(courier))).andExpect(status().isOk());
        }
        catch (Exception ex){
            fail();
        }
    }

    @Test
    void postCourierPackageSize_ReturnBadRequest() {
        try{
            when(couriersService.save(courier)).thenThrow(new Exception());
            this.mockMvc.perform(post("/api/couriers/change-packagesize")).andExpect(status().isBadRequest());
        }
        catch (Exception ex){
            fail();
        }
    }

    private CourierDTO getCourierDTO(){
        CourierDTO courierDTO = new CourierDTO();
        courierDTO.setId(1);
        courierDTO.setFullName("Courier 01");
        return courierDTO;
    }

    private Schedule getSchedule(){
        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setStartTime(new Date());
        schedule.setEndTime(new Date());
        return schedule;
    }

    private ScheduleDTO getScheduleDTO(){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setStartTime(new Date());
        scheduleDTO.setEndTime(new Date());
        scheduleDTO.setNrScheduledCouriers(1);
        return scheduleDTO;
    }

    private SimpleDateFormat getSimpleDateFormat(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf;
    }

    private Courier getCourier(){
        Courier courier = new Courier();
        courier.setId(0);
        courier.setKvkNumber(11111111);
        return courier;
    }
}
