package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.Services.RouteService;
import CIMSOLUTIONS.SnelTransport.Models.OrderItem;
import CIMSOLUTIONS.SnelTransport.Models.Route;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class RouteController {

    @Autowired
    RouteService routeService;

    /**
     * This function fetches the Route object from the database and converts it in a json format. This json format is an
     * array of Routes, each route had: an id, index, startAddress (each Address containts: id, city, street, country, housenumber, zipcode,
     * longitude and latitude), endAddress and a json array with OrderItems.
     * @param date
     * @return
     * @throws Exception
     */
    @GetMapping("/routes/{date}")
    public ResponseEntity<List<Route>> getAllRoutes(@PathVariable String date) throws Exception {
        try {
            return ResponseEntity.ok(routeService.getWithDate(date));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * This function fetches the Route object from the database and converts it in a json format. This json format is an
     * array of Routes, each route had: an id, index, startAddress (each Address containts: id, city, street, country, housenumber, zipcode,
     * longitude and latitude), endAddress and a json array with OrderItems.
     * @param courierid
     * @param scheduleid
     * @return
     * @throws Exception
     */
    @GetMapping("/my-route/{courierid}/{scheduleid}")
    public ResponseEntity<List<Route>> getAllRoutesCourier(@PathVariable int courierid, @PathVariable int scheduleid) throws Exception {
        try {
            return ResponseEntity.ok(routeService.get(courierid,scheduleid));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
