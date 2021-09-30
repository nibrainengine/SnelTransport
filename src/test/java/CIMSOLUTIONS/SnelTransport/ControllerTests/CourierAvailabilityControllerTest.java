package CIMSOLUTIONS.SnelTransport.ControllerTests;

import CIMSOLUTIONS.SnelTransport.Services.CourierAvailabilityService;
import class_objects.AvailablePeriod;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourierAvailabilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourierAvailabilityService courierAvailabilityService;

    @Autowired
    private ObjectMapper objectMapper;

    private AvailablePeriod availablePeriod;

    @BeforeEach
    public void setup(){
        availablePeriod = getAvailablePeriod();
    }

    @Test
    public void testGet() throws Exception {
        List<AvailablePeriod> availablePeriods = Collections.singletonList(availablePeriod);
        when(courierAvailabilityService.get(availablePeriod.getCourierId())).thenReturn(availablePeriods);
        SimpleDateFormat sdf = getSimpleDateFormat();

        this.mockMvc.perform(get("/available-periods/" + availablePeriod.getCourierId())).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(availablePeriod.getId()))
                .andExpect(jsonPath("$[0].courierId").value(availablePeriod.getCourierId()))
                .andExpect(jsonPath("$[0].startTime").value(sdf.format(availablePeriod.getStartTime())))
                .andExpect(jsonPath("$[0].endTime").value(sdf.format(availablePeriod.getEndTime())))
                .andExpect(jsonPath("$[0].price").value(availablePeriod.getPrice()))
                .andExpect(jsonPath("$[0].approved").value(availablePeriod.getApproved()));
    }

    @Test
    public void testGetBadRequest() throws Exception {
        when(courierAvailabilityService.get(availablePeriod.getCourierId())).thenThrow(Exception.class);
        this.mockMvc.perform(get("/available-periods/" + availablePeriod.getCourierId()))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testPost() throws Exception {
        when(courierAvailabilityService.create(availablePeriod)).thenReturn(1);
        this.mockMvc.perform(post("/available-periods/")
                .contentType("application/json").content(objectMapper.writeValueAsString(availablePeriod)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testPostBadRequest() throws Exception {
        when(courierAvailabilityService.create(any(AvailablePeriod.class))).thenThrow(Exception.class);
        this.mockMvc.perform(post("/available-periods/"))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testPostBadRequestWrongBody() throws Exception {
        when(courierAvailabilityService.create(any(AvailablePeriod.class))).thenReturn(1);
        this.mockMvc.perform(post("/available-periods/")
                .contentType("application/json").content(objectMapper.writeValueAsString(1)))
                .andDo(print()).andExpect(status().isBadRequest());
    }



    private SimpleDateFormat getSimpleDateFormat(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf;
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
