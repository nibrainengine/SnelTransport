package CIMSOLUTIONS.SnelTransport.ServicesTests;
import CIMSOLUTIONS.SnelTransport.DAO.PickUpHubDAO;
import CIMSOLUTIONS.SnelTransport.Models.Address;
import CIMSOLUTIONS.SnelTransport.Models.PickUpHub;
import CIMSOLUTIONS.SnelTransport.Services.PickUpHubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

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

//    @Test
//    void testSave() throws Exception {
//    when(pickUpHubDAO.postPickupHub(pickUpHub));
//    assertEquals()
//    }



    private PickUpHub getPickUpHub(){
        PickUpHub pickUpHub = new PickUpHub();
        pickUpHub.setUrl("http://localhost:8080/api/MockPickupData");
        pickUpHub.setAddress(new Address("straat", "nummer", "postcode", "stad", "land", 12.5, 3.3));
        return pickUpHub;
    }
}
