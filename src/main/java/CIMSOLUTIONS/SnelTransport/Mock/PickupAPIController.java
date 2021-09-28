package CIMSOLUTIONS.SnelTransport.Mock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class PickupAPIController {
    //RETURNS A JSON OBJECT OF A FAKE PICKUP HUB
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
