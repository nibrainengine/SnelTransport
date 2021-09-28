package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.DAO.PickuphubDAO;
import CIMSOLUTIONS.SnelTransport.Mock.PickupAPI;
import CIMSOLUTIONS.SnelTransport.Mock.PickupProduct;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@RestController
@RequestMapping("/api")
public class PickupDataController {
    PickuphubDAO pickuphubDAO = new PickuphubDAO();
    @CrossOrigin
    @ResponseBody()
    @RequestMapping(value = "/GetPickupData", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPickupData() throws IOException, JSONException {
        // Pretend we get stuff from the database HERE
        pickuphubDAO.getURLS();
        ArrayList<String> pickUpAPIs = new ArrayList<>();
        pickUpAPIs.add("http://localhost:8080/api/MockPickupData");
        pickUpAPIs.add("http://localhost:8080/api/MockPickupData");
        //Add their URLs to this list, later names aswell for more clarity

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
