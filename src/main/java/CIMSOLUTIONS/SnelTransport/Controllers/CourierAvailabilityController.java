package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.Services.CourierAvailabilityService;
import CIMSOLUTIONS.SnelTransport.Models.AvailablePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/available-periods")
@CrossOrigin(origins = "*")
public class CourierAvailabilityController {

    private CourierAvailabilityService courierAvailabilityService;

    @Autowired
    public void setInjectedBean(CourierAvailabilityService courierAvailabilityService) {
        this.courierAvailabilityService = courierAvailabilityService;
    }

    /**
     * Gets all available periods from a courier
     * @param courierId identification of a courier, need to be sent in the URL.
     * @return List<AvailablePeriod> AvailablePeriod contains data on when a courier is available
     */
    @GetMapping("/{courierId}")
    public ResponseEntity<List<AvailablePeriod>> get(@PathVariable int courierId) {
        try {
            return ResponseEntity.ok(courierAvailabilityService.get(courierId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Posts new available period of a courier
     * @param availablePeriod contains data on when a courier is available, need to be sent in the body of the request.
     * @return int (id of new availablePeriod in database)
     */
    @PostMapping()
    public ResponseEntity<Integer> post(@RequestBody AvailablePeriod availablePeriod) {
        try {
            return ResponseEntity.ok(courierAvailabilityService.create(availablePeriod));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Put call that approves an available period
     * @param periodId primary of the id that will be changed
     * @return nothing
     */
    @PutMapping("/{periodId}")
    public ResponseEntity<AvailablePeriod> put(@PathVariable int periodId){
        try {
            return ResponseEntity.ok(courierAvailabilityService.approve(periodId));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
