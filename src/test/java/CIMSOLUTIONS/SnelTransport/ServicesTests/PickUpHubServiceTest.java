package CIMSOLUTIONS.SnelTransport.ServicesTests;
import CIMSOLUTIONS.SnelTransport.DAO.PickUpHubDAO;
import CIMSOLUTIONS.SnelTransport.DTO.PickUpAPIDTO;
import CIMSOLUTIONS.SnelTransport.Models.Address;
import CIMSOLUTIONS.SnelTransport.Models.PickUpHub;
import CIMSOLUTIONS.SnelTransport.Services.PickUpHubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PickUpHubServiceTest {

    @InjectMocks
    PickUpHubService pickUpHubService;

    @Mock
    private PickUpHubDAO pickUpHubDAO;

    private PickUpHub pickUpHub;

    @BeforeEach
    public void setup(){
        pickUpHub = getPickUpHub();
    }

    @Test
    void testGoodSave() throws Exception {
    doNothing().when(pickUpHubDAO).postPickupHub(pickUpHub);
    pickUpHubService.save(pickUpHub);
    }

    @Test
    void testGetActiveAPIsWithURLs(){
        List<PickUpHub> pickUpHubs = Collections.singletonList(pickUpHub);
        when(pickUpHubDAO.getURLsandAddresses()).thenReturn(pickUpHubs);
        assertEquals(pickUpHubs, pickUpHubService.getActiveAPIsWithAdresses());
        assertSame(pickUpHubService.getActiveAPIsWithAdresses().get(0).getClass(), PickUpHub.class);
    }

    @Test
    void testStatusUpdate(){
        PickUpHub pickUpHub = getPickUpHub();
        when(pickUpHubDAO.enableDisablePickup(pickUpHub.getId())).thenReturn(pickUpHub);
        assertSame(pickUpHubService.enableDisablePickup(pickUpHub.getId()).getClass(), PickUpHub.class);
    }

    @Test
    void testGetAPIS() throws Exception{
        PickUpAPIDTO pickUpAPIDTO = getPickupAPIDTO();
        List<PickUpAPIDTO> pickUpAPIDTOS = Collections.singletonList(pickUpAPIDTO);
        when(pickUpHubDAO.getAPIs()).thenReturn(pickUpAPIDTOS);
        assertEquals(pickUpAPIDTOS, pickUpHubService.getAPIs());
        assertSame(pickUpHubService.getAPIs().get(0).getClass(), PickUpAPIDTO.class);
    }


    private PickUpAPIDTO getPickupAPIDTO() {
        return new PickUpAPIDTO(1, "http://test.nl", getAddress(), false);
    }
    private Address getAddress(){
        return new Address("straat", "nummer", "postcode", "stad", "land", 12.5, 3.3);
    }

    private PickUpHub getPickUpHub(){
        PickUpHub pickUpHub = new PickUpHub();
        pickUpHub.setUrl("http://localhost:8080/api/MockPickupData");
        pickUpHub.setAddress(new Address("straat", "nummer", "postcode", "stad", "land", 12.5, 3.3));
        return pickUpHub;
    }
}
