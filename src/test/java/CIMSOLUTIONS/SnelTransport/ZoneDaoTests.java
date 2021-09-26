package CIMSOLUTIONS.SnelTransport;

import CIMSOLUTIONS.SnelTransport.Zone.IZoneDao;
import CIMSOLUTIONS.SnelTransport.Zone.ZoneController;
import CIMSOLUTIONS.SnelTransport.Zone.ZoneDao;
import class_objects.Zone;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

@SpringBootTest
public class ZoneDaoTests {

    IZoneDao zoneDao = Mockito.mock(ZoneDao.class);

    @Autowired
    private ZoneController controller;

    @Test
    void getAllZones() throws Exception{
        ArrayList zones = new ArrayList<Zone>();
        zones.add(new Zone(0, "test"));
        when(zoneDao.getAll()).thenReturn(zones);
        assertEquals(1, zoneDao.getAll().size());
    }

    @Test
    void addZone() throws Exception{
        Zone zone = new Zone(0, "testZone");
        when(zoneDao.save(zone)).thenReturn(zone);
        assertEquals(zone, zoneDao.save(zone));
    }
}
