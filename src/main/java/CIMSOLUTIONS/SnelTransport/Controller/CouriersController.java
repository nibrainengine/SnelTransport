package CIMSOLUTIONS.SnelTransport.Controller;

import CIMSOLUTIONS.SnelTransport.Services.CourierScheduleService;
import CIMSOLUTIONS.SnelTransport.Services.CouriersService;
import CIMSOLUTIONS.SnelTransport.class_objects.Schedule;
import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
}
