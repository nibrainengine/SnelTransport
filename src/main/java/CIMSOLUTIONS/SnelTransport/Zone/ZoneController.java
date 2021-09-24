package CIMSOLUTIONS.SnelTransport.Zone;

import class_objects.Zone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class ZoneController {
    private ZoneDao zoneDao = new ZoneDao();

    /**
     * Add a new zone
     *
     * @param zone zone in JSON
     * @return Http response with Arraylist<Zone>
     */
    @PostMapping("/zone")
    public ResponseEntity<Zone> postZone(@RequestBody Zone zone) {
        try {
            zoneDao.save(zone);
            return ResponseEntity.ok(zone);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Get all zones
     *
     * @return Http response with Arraylist<Zone>
     */
    @GetMapping("/zone")
    public ResponseEntity<ArrayList<Zone>> getAllZones() {
        try {
            ArrayList<Zone> zones = zoneDao.getAll();
            return ResponseEntity.ok(zones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
