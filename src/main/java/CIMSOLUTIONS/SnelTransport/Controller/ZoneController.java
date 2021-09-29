package CIMSOLUTIONS.SnelTransport.Controller;

import CIMSOLUTIONS.SnelTransport.Dao.ZoneDao;
import class_objects.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("zone")
public class ZoneController {

    @Autowired
    ZoneDao zoneDao;

    /**
     * Add a new zone
     *
     * @param zone zone in JSON
     * @return Http response with Zone
     */
    @RequestMapping(method = RequestMethod.POST, path = "")
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
     * @return Http response with List<Zone>
     */
    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<List<Zone>> getAllZones() {
        try {
            List<Zone> zones = zoneDao.getAll();
            return ResponseEntity.ok(zones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Delete requested zone using id
     * @param id id of zone to delete
     * @return Http response
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<String> deleteZone(@PathVariable int id){
        try{
            zoneDao.delete(id);
            return ResponseEntity.ok("success");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not remove zone");
        }
    }
}
