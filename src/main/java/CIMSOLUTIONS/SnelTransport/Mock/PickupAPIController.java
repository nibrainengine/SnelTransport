package CIMSOLUTIONS.SnelTransport.Mock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
     * @throws JSONException
     */
    @GetMapping("/MockPickupData")
    @ResponseBody
    public String getMockedPickupData() throws JSONException {
        PickupAPI pickupAPI = new PickupAPI();
        JSONArray jsonA = new JSONArray();
        for(PickupProduct product : pickupAPI.getProducts()){
            JSONObject json = new JSONObject();
            json.put("productID", product.getProductID());
            json.put("arrivalDate", product.getArrivedOn());
            jsonA.put(json);
        }
        return jsonA.toString();
    }

}
