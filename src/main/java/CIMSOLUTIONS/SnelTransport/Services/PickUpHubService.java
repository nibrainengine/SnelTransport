package CIMSOLUTIONS.SnelTransport.Services;
import CIMSOLUTIONS.SnelTransport.DAO.PickUpHubDAO;
import CIMSOLUTIONS.SnelTransport.DTO.PickUpAPIDTO;
import CIMSOLUTIONS.SnelTransport.Models.PickUpHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickUpHubService {

    private PickUpHubDAO pickUpHubDAO;

    @Autowired
    public void setInjectedBean(PickUpHubDAO pickUpHubDAO){
        this.pickUpHubDAO = pickUpHubDAO;
    }

    /**
     * This method queries the database to collect all PickupHubs that have an API, these API's are then requested for their products using their URL. Results as well as general information about these Pickup hubs are returned to the frontend in JSON format.
     *
     * @return String A JSON Object holding a list of PickupHubs, each with their own address, url and products
     */
    public List<PickUpHub> getActiveAPIsWithAdresses() {
        return pickUpHubDAO.getURLsandAddresses();
    }

    /**
     * This call allows the user to submit a pickuphub alongside its API URL into our database.
     *
     * @param pickUpHub A JSON format PickupHub must be added to the body of the HTTP Request, this consists of 2 parts, an address object and a url.
     * @return This call returns a generic "Success!" string and response code 200.
     */
    public void save(PickUpHub pickUpHub) throws Exception {
        pickUpHubDAO.postPickupHub(pickUpHub);
    }

    /**
     * Returns the information of all registered pickup hubs. Returns pickup hubs without API's aswell as disabled pickup hubs.
     * @return List of all pickup hubs registered in the database.
     */
    public List<PickUpAPIDTO> getAPIs() throws Exception{
        return pickUpHubDAO.getAPIs();
    }
    /**
     * Swaps the status of a pickup hub from disabled to enabled, or the other way around, both in the same call.
     *
     * @param id Primary Key from the Pickup hub table.
     * @return PickUpHub    Copy of the PickUpHub object with its newly set status.
     */
    public PickUpHub enableDisablePickup(int id){
        return pickUpHubDAO.enableDisablePickup(id);
    }
}
