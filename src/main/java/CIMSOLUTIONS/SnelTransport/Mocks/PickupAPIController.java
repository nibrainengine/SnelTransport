package CIMSOLUTIONS.SnelTransport.Mocks;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Fake controller class that pretends that a PickupHub is running on the same URL as our API.
 */
@RestController
@RequestMapping("/api")
public class PickupAPIController {

    /**
     * This method creates a new Pickup API includings its products and then returns it as JSON.
     * Every time this method is called a new fake PickupAPI is created. Each of these has different data.
     * @return JSON object of its fake Pickup API.
     */
    @RequestMapping(value = "/MockPickupData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PickupProduct>> getMockedPickupData(){
        PickupAPI pickupAPI = new PickupAPI();
        return ResponseEntity.ok(pickupAPI.getProducts());
    }

}
