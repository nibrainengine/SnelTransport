package CIMSOLUTIONS.SnelTransport.ControllerTests;

import CIMSOLUTIONS.SnelTransport.DTO.PickupDataDTO;
import CIMSOLUTIONS.SnelTransport.Mocks.PickupProduct;
import CIMSOLUTIONS.SnelTransport.Models.Address;
import CIMSOLUTIONS.SnelTransport.Models.PickUpHub;
import CIMSOLUTIONS.SnelTransport.Services.PickUpHubService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PickupDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PickUpHubService pickUpHubService;

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGet() throws Exception {
        PickupProduct pickupProduct = new PickupProduct();
        PickUpHub pickUpHub = getPickUpHub();
        ResponseEntity<PickupProduct[]> products = ResponseEntity.ok(new PickupProduct[]{pickupProduct});
        PickupDataDTO pickupDataDTO = getPickupDataDTO(pickupProduct, pickUpHub.getUrl());
        List<PickupDataDTO> pickupDataDTOS = Collections.singletonList(pickupDataDTO);
        List<PickUpHub> pickUpAPIs = Collections.singletonList(pickUpHub);

        when(pickUpHubService.getActiveAPIsWithAdresses()).thenReturn(pickUpAPIs);
        when(restTemplate.getForEntity(pickUpHub.getUrl(), PickupProduct[].class)).thenReturn(products);

        this.mockMvc.perform(get("/api/GetPickupData/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address.street").value(pickupDataDTOS.get(0).getAddress().getStreet()))
                .andExpect(jsonPath("$[0].url").value(pickupDataDTOS.get(0).getUrl()))
                .andExpect(jsonPath("$[0].products[0].productID").value(pickupDataDTOS.get(0).getProducts().get(0).getProductID()));
    }

    @Test
    public void testPost() throws Exception {
        PickUpHub pickUpHub = getPickUpHub();
        doNothing().when(pickUpHubService).save(pickUpHub);
        this.mockMvc.perform(post("/api/PickupAPI/")
                        .contentType("application/json").content(objectMapper.writeValueAsString(pickUpHub)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testPostServerError() throws Exception {
        PickUpHub pickUpHub = getPickUpHub();
        doThrow(new Exception()).when(pickUpHubService).save(any(PickUpHub.class));
        this.mockMvc.perform(post("/api/PickupAPI/")
                .contentType("application/json").content(objectMapper.writeValueAsString(pickUpHub)))
                .andDo(print()).andExpect(status().is5xxServerError());
    }

    @Test
    public void testPostWrongBody() throws Exception {
        doNothing().when(pickUpHubService).save(any(PickUpHub.class));
        this.mockMvc.perform(post("/api/PickupAPI/")
                        .contentType("application/json").content(objectMapper.writeValueAsString(1)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    private PickUpHub getPickUpHub(){
        PickUpHub pickUpHub = new PickUpHub();
        pickUpHub.setUrl("http://localhost:8080/api/MockPickupData");
        pickUpHub.setAddress(new Address("straat", "nummer", "postcode", "stad", "land", 12.5, 3.3));
        return pickUpHub;
    }

    private PickupDataDTO getPickupDataDTO(PickupProduct pickupProduct, String url){
        Address address = new Address("straat", "nummer", "postcode", "stad", "land", 12.5, 3.3);
        return new PickupDataDTO(address, url, Collections.singletonList(pickupProduct));
    }
}
