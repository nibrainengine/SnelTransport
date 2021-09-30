package CIMSOLUTIONS.SnelTransport.ControllerTests;

import CIMSOLUTIONS.SnelTransport.Services.CouriersService;
import dto.CourierDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
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

    @BeforeEach
    public void setup(){
        courierDTO = getCourierDTO();
    }

    @Test
    public void testGet() throws Exception {
        List<CourierDTO> courierDTOS = Collections.singletonList(courierDTO);
        when(couriersService.getAll()).thenReturn(courierDTOS);

        this.mockMvc.perform(get("/couriers/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(courierDTO.getId()))
                .andExpect(jsonPath("$[0].username").value(courierDTO.getUsername()));
    }

    private CourierDTO getCourierDTO(){
        CourierDTO courierDTO = new CourierDTO();
        courierDTO.setId(1);
        courierDTO.setUsername("Courier 01");
        return courierDTO;
    }
}