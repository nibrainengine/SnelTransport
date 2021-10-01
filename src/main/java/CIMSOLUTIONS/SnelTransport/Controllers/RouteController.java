package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.Services.RouteService;
import CIMSOLUTIONS.SnelTransport.class_objects.OrderItem;
import CIMSOLUTIONS.SnelTransport.class_objects.Route;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class RouteController {

    @Autowired
    RouteService routeService;

    /**
     * This function fetches the Route object from the database and converts it in a json format. This json format is an
     * array of Routes, each route had: an id, index, startAddress (each Address containts: id, city, street, country, housenumber, zipcode,
     * longitude and latitude), endAddress and a json array with OrderItems.
     *
     * path can be: my-routes/1/1
     * @param courierid
     * @param scheduleid
     * @return
     */
    @CrossOrigin
    @GetMapping("my-routes/{courierid}/{scheduleid}")
    public String getAllRoutesCourier(@PathVariable int courierid, @PathVariable int scheduleid) throws Exception {
        try {
            JSONArray jsonArray = new JSONArray();
            for (Route route : routeService.get(courierid, scheduleid)) {
                JSONObject json = new JSONObject();
                json.put("id", route.getId());
                json.put("index", route.getIndex());
                json.put("startTime", route.getStartTime().toString());
                json.put("endTime", route.getEndTime().toString());

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
                jsonEndAdress.put("id", route.getEndAddress().getId());
                jsonEndAdress.put("city", route.getEndAddress().getCity());
                jsonEndAdress.put("country", route.getEndAddress().getCountry());
                jsonEndAdress.put("street", route.getEndAddress().getStreet());
                jsonEndAdress.put("housenumber", route.getEndAddress().getHouseNumber());
                jsonEndAdress.put("zipcode", route.getEndAddress().getZipCode());
                jsonEndAdress.put("latitude", route.getEndAddress().getLatitude());
                jsonEndAdress.put("longitude", route.getEndAddress().getLongitude());
                json.put("endAdress", jsonEndAdress);

                JSONObject jsonDelAdress = new JSONObject();
                jsonDelAdress.put("id", route.getDeliveryAddress().getId());
                jsonDelAdress.put("city", route.getDeliveryAddress().getCity());
                jsonDelAdress.put("country", route.getDeliveryAddress().getCountry());
                jsonDelAdress.put("street", route.getDeliveryAddress().getStreet());
                jsonDelAdress.put("housenumber", route.getDeliveryAddress().getHouseNumber());
                jsonDelAdress.put("zipcode", route.getDeliveryAddress().getZipCode());
                jsonDelAdress.put("latitude", route.getDeliveryAddress().getLatitude());
                jsonDelAdress.put("longitude", route.getDeliveryAddress().getLongitude());
                json.put("deliveryAddress", jsonDelAdress);

                JSONArray jsonArrayOrderItems = new JSONArray();
                for (OrderItem orderItem : route.getOrderItems()) {
                    JSONObject jsonOrderItem = new JSONObject();
                    jsonOrderItem.put("id", orderItem.getId());
                    jsonOrderItem.put("price", orderItem.getPrice());
                    jsonOrderItem.put("eta", orderItem.getEta().toString());
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
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This function fetches the Route object from the database and converts it in a json format. This json format is an
     * array of Routes, each route had: an id, index, startAddress (each Address containts: id, city, street, country, housenumber, zipcode,
     * longitude and latitude), endAddress and a json array with OrderItems.
     *
     * path can be: my-routes/2021-10-01
     * @param date
     * @return
     */
    @CrossOrigin
    @GetMapping("routes/{date}")
    public String getAllRoutes(@PathVariable String date) throws Exception {
        try {
            JSONArray jsonArray = new JSONArray();
            for (Route route : routeService.getWithDate(date)) {
                JSONObject json = new JSONObject();
                json.put("id", route.getId());
                json.put("index", route.getIndex());
                json.put("startTime", route.getStartTime().toString());
                json.put("endTime", route.getEndTime().toString());

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
                jsonEndAdress.put("id", route.getEndAddress().getId());
                jsonEndAdress.put("city", route.getEndAddress().getCity());
                jsonEndAdress.put("country", route.getEndAddress().getCountry());
                jsonEndAdress.put("street", route.getEndAddress().getStreet());
                jsonEndAdress.put("housenumber", route.getEndAddress().getHouseNumber());
                jsonEndAdress.put("zipcode", route.getEndAddress().getZipCode());
                jsonEndAdress.put("latitude", route.getEndAddress().getLatitude());
                jsonEndAdress.put("longitude", route.getEndAddress().getLongitude());
                json.put("endAdress", jsonEndAdress);

                JSONObject jsonDelAdress = new JSONObject();
                jsonDelAdress.put("id", route.getDeliveryAddress().getId());
                jsonDelAdress.put("city", route.getDeliveryAddress().getCity());
                jsonDelAdress.put("country", route.getDeliveryAddress().getCountry());
                jsonDelAdress.put("street", route.getDeliveryAddress().getStreet());
                jsonDelAdress.put("housenumber", route.getDeliveryAddress().getHouseNumber());
                jsonDelAdress.put("zipcode", route.getDeliveryAddress().getZipCode());
                jsonDelAdress.put("latitude", route.getDeliveryAddress().getLatitude());
                jsonDelAdress.put("longitude", route.getDeliveryAddress().getLongitude());
                json.put("deliveryAddress", jsonDelAdress);

                JSONArray jsonArrayOrderItems = new JSONArray();
                for (OrderItem orderItem : route.getOrderItems()) {
                    JSONObject jsonOrderItem = new JSONObject();
                    jsonOrderItem.put("id", orderItem.getId());
                    jsonOrderItem.put("price", orderItem.getPrice());
                    jsonOrderItem.put("eta", orderItem.getEta().toString());
                    jsonOrderItem.put("orderStatus", orderItem.getOrderStatus());
                    jsonOrderItem.put("quantity", orderItem.getQuantity());

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
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        }
}