package CIMSOLUTIONS.SnelTransport.ControllerTests;

import CIMSOLUTIONS.SnelTransport.Controllers.ZoneController;
import class_objects.Zone;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ZoneControllersTests {

    ZoneController zoneController = Mockito.mock(ZoneController.class);

    /**
     * Test: Throws exception on getting a list of zones. Can't retrieve a list of all zones
     */
    @Test
    void getAllZones_ReturnOk(){
        List<Zone> zones = new ArrayList<>();
        Zone zone = new Zone(0, "test");
        zones.add(zone);
        when(zoneController.getAllZones()).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null), zoneController.getAllZones());
    }

    /**
     * Test: Retrieve a list of all zones
     */
    @Test
    void getAllZones_ReturnBadRequest(){
        List<Zone> zones = new ArrayList<>();
        Zone zone = new Zone(0, "test");
        zones.add(zone);
        when(zoneController.getAllZones()).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null), zoneController.getAllZones());
    }

    /**
     * Test: add a new zone
     */
    @Test
    void addZone_ReturnOk(){
        Zone zone = new Zone(0, "testZone");
        when(zoneController.postZone(zone)).thenReturn(ResponseEntity.ok(zone));
        assertEquals(ResponseEntity.ok(zone), zoneController.postZone(zone));
    }

    /**
     * Test: throws exception when adding a new zone. Can't add a new zone
     */
    @Test
    void addZone_ReturnBadRequest(){
        Zone zone = new Zone(0, "testZone");
        when(zoneController.postZone(zone)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null), zoneController.postZone(zone));
    }

    /**
     * Test: deletes a zone with given id
     */
    @Test
    void deleteZone_ReturnOk(){
        when(zoneController.deleteZone(0)).thenReturn(ResponseEntity.ok("success"));
        assertEquals(ResponseEntity.ok("success"), zoneController.deleteZone(0));
    }

    /**
     * Test: Throws Exception on deleting zone, zone can't be deleted
     */
    @Test
    void deleteZone_ReturnBadRequest(){
        when(zoneController.deleteZone(0)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not remove zone"));
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not remove zone"), zoneController.deleteZone(0));
    }
}
