package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.Services.CourierAvailabilityService;
import class_objects.AvailablePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("available-periods")
@CrossOrigin(origins = "*")
public class CourierAvailabilityController {

    private CourierAvailabilityService courierAvailabilityService;

    @Autowired
    public void setInjectedBean(CourierAvailabilityService courierAvailabilityService) {
        this.courierAvailabilityService = courierAvailabilityService;
    }

    /**
     * Gets all available periods from a courier
     * @return List<AvailablePeriod>
     */
    @GetMapping("/{courierId}")
    public List<AvailablePeriod> get(@PathVariable int courierId) {
        return courierAvailabilityService.get(courierId);
    }

    /**
     * Posts new available period of a courier
     * @return int (id of new availablePeriod in database)
     */
    @PostMapping()
    public int post(@RequestBody AvailablePeriod availablePeriod) {
        return courierAvailabilityService.create(availablePeriod);
    }
}
