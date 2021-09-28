package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.DAO.PickuphubDAO;
import CIMSOLUTIONS.SnelTransport.Mock.PickupAPI;
import CIMSOLUTIONS.SnelTransport.Mock.PickupProduct;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api")

public class PickupDataController {

    @Autowired
    PickuphubDAO pickuphubDAO;
    @CrossOrigin
    @ResponseBody()
    @RequestMapping(value = "/GetPickupData", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPickupData() throws IOException, JSONException {
        List<String> pickUpAPIs = pickuphubDAO.getURLS();

        JSONArray responses = new JSONArray();
        for(String pickupAPI : pickUpAPIs) {
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
                JSONObject pickupURL = new JSONObject().put("url", pickupAPI);
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



}
