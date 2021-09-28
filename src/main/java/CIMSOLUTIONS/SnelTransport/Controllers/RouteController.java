package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.DAO.RouteDAO;
import CIMSOLUTIONS.SnelTransport.Services.RouteService;
import CIMSOLUTIONS.SnelTransport.class_objects.OrderItem;
import CIMSOLUTIONS.SnelTransport.class_objects.Route;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class RouteController {

    @Autowired
    RouteService routeService;

    @CrossOrigin
    @GetMapping("my-routes/{courierid}/{scheduleid}")
    public String getAllRoutesCourier(@PathVariable int courierid, @PathVariable int scheduleid) {
        JSONArray jsonArray = new JSONArray();
        for (Route route : routeService.get(courierid, scheduleid)){
            JSONObject json = new JSONObject();
            json.put("id", route.getId());
            json.put("index", route.getIndex());

            JSONObject jsonStartAdress = new JSONObject();
            jsonStartAdress.put("id", route.getStartAddress().getId());
            jsonStartAdress.put("city", route.getStartAddress().getCity());
            jsonStartAdress.put("country", route.getStartAddress().getCountry());
            jsonStartAdress.put("street", route.getStartAddress().getStreet());
            jsonStartAdress.put("housenumber", route.getStartAddress().getHouseNumber());
            jsonStartAdress.put("zipcode", route.getStartAddress().getZipCode());
            jsonStartAdress.put("latitude", route.getStartAddress().getLatitude());
            jsonStartAdress.put("longitude", route.getStartAddress().getLongitude());
            json.put("startAdress", jsonStartAdress);

            JSONObject jsonEndAdress = new JSONObject();
            jsonEndAdress.put("id", route.getStartAddress().getId());
            jsonEndAdress.put("city", route.getStartAddress().getCity());
            jsonEndAdress.put("country", route.getStartAddress().getCountry());
            jsonEndAdress.put("street", route.getStartAddress().getStreet());
            jsonEndAdress.put("housenumber", route.getStartAddress().getHouseNumber());
            jsonEndAdress.put("zipcode", route.getStartAddress().getZipCode());
            jsonEndAdress.put("latitude", route.getStartAddress().getLatitude());
            jsonEndAdress.put("longitude", route.getStartAddress().getLongitude());
            json.put("endAdress", jsonEndAdress);

            JSONArray jsonArrayOrderItems = new JSONArray();
            for(OrderItem orderItem : route.getOrderItems()){
                JSONObject jsonOrderItem = new JSONObject();
                jsonOrderItem.put("id", orderItem.getId());
                jsonOrderItem.put("price", orderItem.getPrice());
                jsonOrderItem.put("eta", orderItem.getEta());
                jsonOrderItem.put("orderStatus", orderItem.getOrderStatus());
                jsonOrderItem.put("quantity", orderItem.getQuantity());

                JSONObject jsonDeliveryAdress = new JSONObject();
                jsonDeliveryAdress.put("id", orderItem.getDeliveryAddress().getId());
                jsonDeliveryAdress.put("city", orderItem.getDeliveryAddress().getCity());
                jsonDeliveryAdress.put("country", orderItem.getDeliveryAddress().getCountry());
                jsonDeliveryAdress.put("street", orderItem.getDeliveryAddress().getStreet());
                jsonDeliveryAdress.put("housenumber", orderItem.getDeliveryAddress().getHouseNumber());
                jsonDeliveryAdress.put("zipcode", orderItem.getDeliveryAddress().getZipCode());
                jsonDeliveryAdress.put("latitude", orderItem.getDeliveryAddress().getLatitude());
                jsonDeliveryAdress.put("longitude", orderItem.getDeliveryAddress().getLongitude());
                jsonOrderItem.put("deliveryAdress", jsonDeliveryAdress);

                JSONObject jsonProduct = new JSONObject();
                jsonProduct.put("id", orderItem.getProduct().getId());
                jsonProduct.put("name", orderItem.getProduct().getName());
                jsonProduct.put("size", orderItem.getProduct().getSize());
                jsonProduct.put("price", orderItem.getProduct().getPrice());
                jsonProduct.put("category", orderItem.getProduct().getCategories());
                jsonOrderItem.put("product", jsonProduct);
                jsonArrayOrderItems.add(jsonOrderItem);
            }
            json.put("orderItems", jsonArrayOrderItems);
            jsonArray.add(json);
        }
        return jsonArray.toJSONString();
    }
}
