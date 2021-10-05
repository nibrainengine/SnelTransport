package CIMSOLUTIONS.SnelTransport.ControllerTests;

import CIMSOLUTIONS.SnelTransport.DAO.PickUpHubDAO;
import CIMSOLUTIONS.SnelTransport.DTO.PickupDataDTO;
import CIMSOLUTIONS.SnelTransport.Models.PickUpHub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PickupControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private PickUpHubDAO pickUpHubDAO;

    private PickUpHub pickUpHub;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        pickUpHubDAO = new PickUpHubDAO();
    }

    @Test
    public void testGetPickups() throws Exception{
        this.mockMvc.perform(get("/api/GetPickupData")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address.street").value("Symfonielaan"));
    }


        //Ik weet niet hoe ik dit moet testen omdat dit de SimpleJdbcInsert vereist die jdbc.GetDataSource vereist die niet bestaat
//    @Test
//    public void postPickup() throws Exception{
//        PickUpHub pickupHub = new PickUpHub(new Address("Street", "12B", "city", "zipCode", "country", 12.0, 25.2), false, "http://localhost:8080/api/MockPickupData");
//        when(pickUpHubDAO.postPickupHub(pickupHub)).thenReturn(1);
//        this.mockMvc.perform(post("/api/PickupAPI").contentType("application/json").content(objectMapper.writeValueAsString(pickupHub)))
//                .andDo(print())
//                .andExpect(jsonPath("$.url").value("http://localhost:8080/api/MockPickupData"));
//    }

}
