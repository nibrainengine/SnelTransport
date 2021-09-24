package CIMSOLUTIONS.SnelTransport;

import CIMSOLUTIONS.SnelTransport.Mock.PickupAPI;
import CIMSOLUTIONS.SnelTransport.Mock.PickupProduct;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@RestController
@RequestMapping("/api")
public class PickupData {
    public PickupData() {
    }


    //RETURNS A JSON OBJECT OF A FAKE PICKUP HUB
    @GetMapping("/MockPickupData")
    @ResponseBody
    public String getMockedPickupData() throws JSONException {
        PickupAPI pickupAPI = new PickupAPI();
        JSONArray jsonA = new JSONArray();
        for(PickupProduct product : pickupAPI.getProducts()){
            JSONObject json = new JSONObject();
            json.put("produtID", product.getProductID());
            json.put("arrivalDate", product.getArrivedOn());
            jsonA.put(json);
        }
        return jsonA.toString();
    }


    @GetMapping("/GetPickupData")
    @ResponseBody
    public String getPickupData() throws IOException, JSONException {
        // Pretend we get stuff from the database HERE
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
                JSONObject singleResponse = new JSONObject().put(pickupAPI, jsonArr);
                responses.put(singleResponse);
            }
            else {
                JSONObject singleResponse = new JSONObject().put(pickupAPI, "Error code: " +responseCode);
                responses.put(singleResponse);
            }
        }
        return responses.toString();
    }



}
