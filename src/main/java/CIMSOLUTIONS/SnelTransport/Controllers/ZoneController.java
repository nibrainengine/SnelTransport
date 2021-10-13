package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.DTO.ZoneDTO;
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
     * Fetches all zone requests from the databases and converts this into a json format (an array of ZoneDTO's with a
     * zoneId, zoneTitle, CourierId and CourierName (ie a combination of several tables enabling the displaying of
     * useful information to the user and allowing the correct selection of the zone request).
     * @return List<ZoneDTO>
     */
    @GetMapping("/requests")
    public List<ZoneDTO> getAllZoneRequests(){
        return zoneService.getAllZoneRequests();
    }

    /**
     * Put function that updates the courierZone table by changing the isApproved field of the specified courierId and
     * zoneId from false to true, or by removing the specified row if the request is rejected.
     * @param zoneId - The id of the zone that is accepted or rejected
     * @param courierId - The id of the courier whose zone is accepted or rejected
     * @param accepted - boolean indicating whether the zone request has been accepted or rejected
     * @return ResponseEntity.ok with 1 being successful and 0 not
     */
    @PostMapping("/requests/{zoneId}/{courierId}")
    public ResponseEntity<Integer> handleZoneRequest(@PathVariable int zoneId, @PathVariable int courierId,
                                                     @RequestBody boolean accepted){
        try {
            return ResponseEntity.ok(zoneService.handleZoneRequest(zoneId, courierId, accepted));
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
