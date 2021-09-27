package CIMSOLUTIONS.SnelTransport.Controllers;

import class_objects.AvailablePeriod;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("available-periods")
@CrossOrigin(origins = "*")
public class CourierAvailabilityController {

    @GetMapping("/{id}")
    public List<AvailablePeriod> get(@PathVariable int id) {
        System.out.println("GET!");
        AvailablePeriod availablePeriod = new AvailablePeriod();
        availablePeriod.setId(id);
        availablePeriod.setStartTime(new Date());

        // TODO remove test data:
        Date endDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.HOUR, 2);
        endDate = calendar.getTime();

        availablePeriod.setEndTime(endDate);
        availablePeriod.setPrice(11.9);
        availablePeriod.setApproved(true);
        return Collections.singletonList(availablePeriod);
    }

    @PostMapping()
    public int post(@RequestBody AvailablePeriod availablePeriod) {
        availablePeriod.setId(3);
        System.out.println(availablePeriod.toString());
        return availablePeriod.getId();
    }
}
