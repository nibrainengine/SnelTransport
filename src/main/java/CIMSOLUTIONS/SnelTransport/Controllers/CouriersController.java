package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.DTO.CancelCourierScheduleRequestDTO;
import CIMSOLUTIONS.SnelTransport.DTO.CourierDTO;
import CIMSOLUTIONS.SnelTransport.Models.Courier;
import CIMSOLUTIONS.SnelTransport.Services.CourierScheduleService;
import CIMSOLUTIONS.SnelTransport.Services.CouriersService;
import CIMSOLUTIONS.SnelTransport.Models.Schedule;
import CIMSOLUTIONS.SnelTransport.DTO.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("api/couriers")
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
     * of Schedules with an id, start and endtime.
     * @param courierId the id of the courier whose schedule is required
     * @return List<Schedule>
     */
    @GetMapping("/{courierId}/schedule")
    public List<Schedule> getSchedule(@PathVariable int courierId){
        return courierScheduleService.getScheduled(courierId);
    }

    /**
     * Fetches all schedules of all couriers from the databases and converts this into a json format (an array
     * of ScheduleDTOs with an id, start, endtime and the amount of couriers working during that half hour block.
     * @return List<ScheduleDTO>
     */
    @GetMapping("/combined-schedules")
    public List<ScheduleDTO> getCombinedSchedules(){
        return courierScheduleService.getCombinedSchedules();
    }

    /**
     * Fetches all schedules of all couriers that work in a specific zone from the databases and converts this into a
     * json format (an array of ScheduleDTOs with an id, start, endtime and the amount of couriers working during that
     * half hour block.
     * @param zoneFilters - a list of courier zoneIds to filter the couriers with
     * @return List<ScheduleDTO>
     */
    @GetMapping("/combined-schedules/filter")
    public List<ScheduleDTO> getCombinedSchedulesFilteredByZones(@RequestParam int[] zoneFilters){
        return courierScheduleService.getCombinedSchedulesFilteredByZones(zoneFilters);
    }

    /**
     * Fetches a single courier of which the account information is required from the database. It gets converted into a json format.
     * Currently this method only fetches the id, kvkNumber and package size. json can be expanded in further iterations.
     * @param courierId the id of the courier whose information is requested.
     * @return Json String of a single courier.
     */
    @GetMapping("/my-info/{courierId}")
    public ResponseEntity<Courier> getAllRoutes(@PathVariable int courierId) {
        try {
            return ResponseEntity.ok(couriersService.getCourierInfo(courierId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Posts a new CancelCourierScheduleRequestDTO
     * @param cancelRequest consists of the schedule ID and the reason for the cancel request
     * @return ResponseEntity<Void> the responseEntity status can be ok or bad request
     */
    @PostMapping("/cancel-schedule")
    public ResponseEntity<Void> postCancelRequest(@RequestBody CancelCourierScheduleRequestDTO cancelRequest){
        try {
            courierScheduleService.insertCancelRequest(cancelRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Post method that alters the package size of a single courier in the database. It returns the altered courier as a json
     * string.
     * @param courier a single courier object with an unique identifier
     * @return Json String of a single courier.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/change-packagesize")
    public ResponseEntity<Courier> postZone(@RequestBody Courier courier) {
        try {
            Courier newPackageSize = couriersService.save(courier);
            return ResponseEntity.ok(newPackageSize);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
