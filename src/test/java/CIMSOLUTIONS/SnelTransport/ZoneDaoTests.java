package CIMSOLUTIONS.SnelTransport;

import CIMSOLUTIONS.SnelTransport.Controller.ZoneController;
import CIMSOLUTIONS.SnelTransport.Dao.ZoneDao;
import class_objects.Zone;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ZoneDaoTests {

    ZoneDao zoneDao = Mockito.mock(ZoneDao.class);

    @Autowired
    private ZoneController controller;

    @Test
    void getAllZones() throws Exception{
        List<Zone> zones = new ArrayList<>();
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
