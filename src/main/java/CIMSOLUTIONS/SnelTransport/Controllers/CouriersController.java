package CIMSOLUTIONS.SnelTransport.Controllers;

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
@RequestMapping("couriers")
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
    @GetMapping()
    public List<CourierDTO> getAll(){
        return couriersService.getAll();
    }

    /**
     * Fetches all schedules of a specified courier from the databases and converts this into a json format (an array
     * of Schedules with an id, start en endtime.
     * @param courierId the id of the courier whose schedule is required
     * @return List<Schedule>
     */
    @GetMapping("{courierId}/schedule") //TODO change URL in frontend: courier -> couriers
    public List<Schedule> getSchedule(@PathVariable int courierId){
        return courierScheduleService.get(courierId);
    }

    /**
     * Posts a new CancelCourierScheduleRequestDTO
     * @param cancelRequest consists of the schedule ID and the reason for the cancel request
     * @return ResponseEntity<Void> the responseEntity status can be ok or bad request
     */
    @PostMapping("cancel-schedule")
    public ResponseEntity<Void> postCancelRequest(@RequestBody CancelCourierScheduleRequestDTO cancelRequest){
        try {
            courierScheduleService.insertCancelRequest(cancelRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
