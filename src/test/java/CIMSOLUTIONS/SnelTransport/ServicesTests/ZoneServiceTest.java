package CIMSOLUTIONS.SnelTransport.ServicesTests;

import CIMSOLUTIONS.SnelTransport.DAO.ZoneDAO;
import CIMSOLUTIONS.SnelTransport.Models.Zone;
import CIMSOLUTIONS.SnelTransport.Models.ZonePoint;
import CIMSOLUTIONS.SnelTransport.Services.ZoneService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ZoneServiceTest {

    @InjectMocks
    private ZoneService zoneService = new ZoneService();

    @Mock
    ZoneDAO zoneDAO;

    private Zone zone;
    private List<Zone> zones;
    private ZonePoint zonePoint;
    private List<ZonePoint> zonePoints;

    @BeforeEach
    public void setup() {
        zone = new Zone(0, "testZone");
        zones = new ArrayList<>();
        zones.add(zone);
        zonePoints = new ArrayList<>();
        zonePoint = new ZonePoint(1, 5.123, 5.123);
    }

    /**
     * Test successful adding zone
     */
    @Test
    void saveZone_Success() {
        try {
            zonePoints.add(zonePoint);
            zonePoints.add(zonePoint);
            zonePoints.add(zonePoint);
            zonePoints.add(zonePoint);
            zone.setZonePoints(zonePoints);

            when(zoneDAO.save(zone)).thenReturn(zone);
            Zone newZone = zoneService.save(zone);
            Assert.assertEquals(newZone.getZoneTitle(), zone.getZoneTitle());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Adding zone should fail when no zone points are given.
     */
    @Test
    void saveZone_ThrowExceptionWhenZonePointsIsNull() {
        try {
            zoneService.save(zone);
            fail();
        } catch (Exception e) {
            Assert.assertNotNull(e);
        }
    }

    /**
     * Adding zone should fail when there are less than 4 zone points given.
     */
    @Test
    void saveZone_ThrowExceptionWhenZonePointsIsLessThan4() {
        try {
            zonePoints.add(zonePoint);
            zone.setZonePoints(zonePoints);
            zoneService.save(zone);
            fail();
        } catch (Exception e) {
            Assert.assertNotNull(e);
        }
    }

    /**
     * Should return list of zones from zoneDAO
     */
    @Test
    void getAllZones_Success(){
        try {
            when(zoneDAO.getAll()).thenReturn(zones);
            List<Zone> getZones = zoneService.getAll();
            Assert.assertEquals(getZones.size(), zones.size());
        } catch (Exception e) {
            fail();
        }
    }
}
