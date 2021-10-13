package CIMSOLUTIONS.SnelTransport.ServicesTests;

import CIMSOLUTIONS.SnelTransport.DAO.ZoneDAO;
import CIMSOLUTIONS.SnelTransport.Models.Zone;
import CIMSOLUTIONS.SnelTransport.Models.ZonePoint;
import CIMSOLUTIONS.SnelTransport.Services.ZoneService;
import org.junit.Assert;
import CIMSOLUTIONS.SnelTransport.DTO.ZoneDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class ZoneServiceTest {

    @InjectMocks
    ZoneService zoneService;

    @Mock
    private ZoneDAO zoneDAO;

    private Zone zone;
    private ZoneDTO zoneDTO;
    private List<Zone> zones;
    private ZonePoint zonePoint;
    private List<ZonePoint> zonePoints;

    @BeforeEach
    public void setup(){
        zone = new Zone(0, "testZone");
        zones = new ArrayList<>();
        zones.add(zone);
        zonePoints = new ArrayList<>();
        zonePoint = new ZonePoint(1, 5.123, 5.123);
        zoneDTO = getZoneDTO();
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

    @Test
    void testGetAllZoneRequests() throws Exception {
        List<ZoneDTO> zoneDTOS = Collections.singletonList(zoneDTO);
        when(zoneDAO.getAllZoneRequests()).thenReturn(zoneDTOS);
        assertEquals(zoneDTOS,zoneService.getAllZoneRequests());
    }

    @Test
    void testGetEmptyZoneRequestsList() {
        assertEquals(Collections.emptyList(),zoneService.getAllZoneRequests());
    }

    @Test
    void testHandleZoneRequest() throws Exception {
        when(zoneDAO.acceptZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId())).thenReturn(1);
        assertEquals(1,zoneService.handleZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId(), true));
        when(zoneDAO.rejectZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId())).thenReturn(1);
        assertEquals(1,zoneService.handleZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId(), false));
    }

    @Test
    void testHandleZoneRequestIs0() throws Exception {
        assertEquals(0,zoneService.handleZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId(), true));
        assertEquals(0,zoneService.handleZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId(), false));
    }

    private ZoneDTO getZoneDTO() {
        ZoneDTO zoneDTO = new ZoneDTO();
        zoneDTO.setZoneId(1);
        zoneDTO.setZoneTitle("Zone 01");
        zoneDTO.setCourierId(1);
        zoneDTO.setCourierName("Courier 01");
        return zoneDTO;
    }
}
