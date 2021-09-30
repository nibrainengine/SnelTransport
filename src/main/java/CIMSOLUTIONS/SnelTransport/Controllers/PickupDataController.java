package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.DAO.PickuphubDAO;
import class_objects.Address;
import class_objects.PickUpHub;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api")

public class PickupDataController {
    @Autowired
    PickuphubDAO pickuphubDAO;

    /**
     * This method queries the database to collect all PickupHubs that have an API, these API's are then requested for their products using their URL. Results as well as general information about these Pickup hubs are returned to the frontend in JSON format.
     * @return String A JSON Object holding a list of PickupHubs, each with their own address, url and products
     * @throws IOException
     * @throws JSONException
     */
    @CrossOrigin
    @ResponseBody()
    @RequestMapping(value = "/GetPickupData", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPickupData() throws IOException, JSONException {
        List<PickUpHub> pickUpAPIs = pickuphubDAO.getURLsandAddresses();
        JSONArray responses = new JSONArray();
        for(PickUpHub pickupHub : pickUpAPIs) {
            String pickupAPI = pickupHub.getUrl();
            URL url = new URL(pickupAPI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            StringBuilder line = new StringBuilder();
            if (responseCode == 200) {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    line.append(sc.nextLine());
                }
                sc.close();
                JSONArray jsonArr = new JSONArray(line.toString());
                JSONObject pickupURL = new JSONObject().put("url", pickupHub.getUrl());
                Address address = pickupHub.getAddress();
                JSONObject addressObject = new JSONObject();
                //Perhaps figure out a way to prevent these addressObject values from shuffling their order.
                //Only aesthetic, json objects are unordered.
                addressObject.put("street", address.getStreet());
                addressObject.put("houseNumber", address.getHouseNumber());
                addressObject.put("zipCode", address.getZipCode());
                addressObject.put("city", address.getCity());
                addressObject.put("country", address.getCountry());
                addressObject.put("latitude", address.getLatitude());
                addressObject.put("longitude", address.getLongitude());

                pickupURL.put("address", addressObject);
                pickupURL.put("products", jsonArr);
                responses.put(pickupURL);
            }
            else {
                JSONObject singleResponse = new JSONObject().put(pickupAPI, "Error code: " +responseCode);
                responses.put(singleResponse);
            }
        }
        return responses.toString();
    }

    /**
     * This call allows the user to submit a pickuphub alongside its API URL into our database.
     * @param pickUpHub A JSON format PickupHub must be added to the body of the HTTP Request, this consists of 2 parts, an address object and a url.
     * @return          This call returns a generic "Success!" string and response code 200.
     */
    @CrossOrigin
    @PostMapping(value = "/PickupAPI", consumes = "application/json", produces="application/json")
    public String addPickupAPI(@RequestBody PickUpHub pickUpHub){
        pickuphubDAO.postPickupHub(pickUpHub);
        return "success!";
    }

}
