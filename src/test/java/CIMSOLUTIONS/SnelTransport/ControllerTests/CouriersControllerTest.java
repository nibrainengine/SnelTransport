package CIMSOLUTIONS.SnelTransport.ControllerTests;

import CIMSOLUTIONS.SnelTransport.Models.Courier;
import CIMSOLUTIONS.SnelTransport.Services.CouriersService;
import CIMSOLUTIONS.SnelTransport.DTO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CouriersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CouriersService couriersService;

    private CourierDTO courierDTO;

    private Courier courier;

    @BeforeEach
    public void setup(){
        courierDTO = getCourierDTO();
    }

    @BeforeEach
    public void setupCourier(){courier = getCourier();}

    @Test
    void getAllRoutesCourier_ReturnOk() throws Exception{
        try{
            when(couriersService.getCourierInfo(1)).thenReturn(this.courier);
            this.mockMvc.perform(get("/api/courier/my-info/1")).andExpect(status().isOk());
        }
        catch (Exception ex){
            fail();
        }
    }

    @Test
    void getAllRoutesCourier_ReturnBadRequest() throws Exception{
        try{
            when(couriersService.getCourierInfo(1)).thenThrow(new Exception());
            this.mockMvc.perform(get("/api/courier/my-info/1")).andExpect(status().isBadRequest());
        }
        catch (Exception ex){
            fail();
        }
    }

    @Test
    public void testGet() throws Exception {
        List<CourierDTO> courierDTOS = Collections.singletonList(courierDTO);
        when(couriersService.getAll()).thenReturn(courierDTOS);

        this.mockMvc.perform(get("/api/couriers/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(courierDTO.getId()))
                .andExpect(jsonPath("$[0].username").value(courierDTO.getUsername()));
    }

    private CourierDTO getCourierDTO(){
        CourierDTO courierDTO = new CourierDTO();
        courierDTO.setId(1);
        courierDTO.setUsername("Courier 01");
        return courierDTO;
    }

    private Courier getCourier(){
        Courier courier = new Courier();
        courier.setId(1);
        courier.setKvkNumber(11111111);
        return courier;
    }
}
