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

        @PostMapping("/zone")
        public Zone postZone(@RequestBody Zone zone) {
            try {
                zoneDao.save(zone);
                return zone;
            } catch (Exception e) {
                return null;
            }
        }


    @GetMapping("/zone")
    public ResponseEntity getAllZones() {
        try {
            ArrayList<Zone> zones = zoneDao.getAll();
            return ResponseEntity.ok(zones);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
