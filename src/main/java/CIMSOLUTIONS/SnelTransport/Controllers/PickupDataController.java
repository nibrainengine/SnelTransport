package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.DAO.PickUpHubDAO;
import CIMSOLUTIONS.SnelTransport.DTO.PickupDataDTO;
import CIMSOLUTIONS.SnelTransport.Mocks.PickupProduct;
import CIMSOLUTIONS.SnelTransport.Models.PickUpHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api")

public class PickupDataController {
    @Autowired
    PickUpHubDAO pickuphubDAO;
    RestTemplate restTemplate = new RestTemplate();

    /**
     * This method queries the database to collect all PickupHubs that have an API, these API's are then requested for their products using their URL. Results as well as general information about these Pickup hubs are returned to the frontend in JSON format.
     *
     * @return String A JSON Object holding a list of PickupHubs, each with their own address, url and products
     */
    @CrossOrigin
    @ResponseBody()
    @RequestMapping(value = "/GetPickupData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PickupDataDTO>> getPickupData() {
        List<PickupDataDTO> responses = new ArrayList<>();
        try {
            List<PickUpHub> pickUpAPIs = pickuphubDAO.getURLsandAddresses();

            for (PickUpHub pickupHub : pickUpAPIs) {
                if (!Objects.equals(pickupHub.getUrl(), "")) {
                    //Incase of bad url in database -> Internal server error
                    try {
                        ResponseEntity<PickupProduct[]> products = restTemplate.getForEntity(pickupHub.getUrl(), PickupProduct[].class);
                        PickupDataDTO pickupDataDTO = new PickupDataDTO(pickupHub.getAddress(), pickupHub.getUrl(), Arrays.asList(products.getBody()));
                        responses.add(pickupDataDTO);
                    }
                    catch (Exception e){
                        //Bad URL exception, skip this one API and move on, not worthy of an error 500.
                    }

                }
            }
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responses);
        }
    }

    /**
     * This call allows the user to submit a pickuphub alongside its API URL into our database.
     *
     * @param pickUpHub A JSON format PickupHub must be added to the body of the HTTP Request, this consists of 2 parts, an address object and a url.
     * @return This call returns a generic "Success!" string and response code 200.
     */
    @CrossOrigin
    @PostMapping(value = "/PickupAPI", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PickUpHub> addPickupAPI(@RequestBody PickUpHub pickUpHub) {

        pickuphubDAO.postPickupHub(pickUpHub);
        return ResponseEntity.ok(pickUpHub);

    }

}
