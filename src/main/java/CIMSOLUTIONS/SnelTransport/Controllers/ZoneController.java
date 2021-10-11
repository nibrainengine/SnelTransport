package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.Services.ZoneService;
import CIMSOLUTIONS.SnelTransport.Models.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/zone")
public class ZoneController {

    @Autowired
    ZoneService zoneService;

    /**
     * Add a new zone
     *
     * @param zone zone in JSON
     * @return Http response with Zone
     */
    @RequestMapping(method = RequestMethod.POST, path = "")
    public ResponseEntity<Zone> postZone(@RequestBody Zone zone) {
        try {
            Zone newZone = zoneService.save(zone);
            return ResponseEntity.ok(newZone);
        }
        catch(DuplicateKeyException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        catch (Exception e) {
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
            List<Zone> zones = zoneService.getAll();
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
            zoneService.delete(id);
            return ResponseEntity.ok("success");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not remove zone");
        }
    }
}
