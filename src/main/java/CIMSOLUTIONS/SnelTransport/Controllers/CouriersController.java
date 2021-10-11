package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.Models.Courier;
import CIMSOLUTIONS.SnelTransport.Services.CourierScheduleService;
import CIMSOLUTIONS.SnelTransport.Services.CouriersService;
import CIMSOLUTIONS.SnelTransport.Models.Schedule;
import CIMSOLUTIONS.SnelTransport.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class CouriersController {

    private CouriersService couriersService;
    private CourierScheduleService courierScheduleService;


    @Autowired
    public void setInjectableBean(CouriersService couriersService, CourierScheduleService courierScheduleService){
        this.couriersService = couriersService;
        this.courierScheduleService = courierScheduleService;
    }

    /**
     * Fetches all couriers from the databases and converts this into a json format (an array of CourierDTO's with an
     * id and username (as opposed to a full Courier which doesn't contain a name but does contain other fields like
     * kvkNumber)).
     * @return List<CourierDTO>
     */
    @GetMapping("couriers")
    public List<CourierDTO> getAll(){
        return couriersService.getAll();
    }

    /**
     * Fetches all schedules of a specified courier from the databases and converts this into a json format (an array
     * of Schedules with an id, start en endtime.
     * @param courierId the id of the courier whose schedule is required
     * @return List<Schedule>
     */
    @GetMapping("courier/{courierId}/schedule")
    public List<Schedule> getSchedule(@PathVariable int courierId){
        return courierScheduleService.get(courierId);
    }

    /**
     * Fetches a single courier of which the account information is required from the database. It gets converted into a json format.
     * Currently this method only fetches the id, kvkNumber and package size. json can be expanded in further iterations.
     * @param courierId the id of the courier whose information is requested.
     * @return Json String of a single courier.
     * @throws Exception
     */
    @GetMapping("courier/my-info/{courierId}")
    public ResponseEntity<Courier> getAllRoutes(@PathVariable int courierId) throws Exception {
        try {
            return ResponseEntity.ok(couriersService.getCourierInfo(courierId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
