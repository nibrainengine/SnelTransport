package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.ZoneDAO;
import CIMSOLUTIONS.SnelTransport.DTO.ZoneDTO;
import CIMSOLUTIONS.SnelTransport.Models.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@JdbcTest
@Sql({"classpath:schema.sql", "classpath:test-data.sql"})
public class ZoneDAOTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ZoneDAO zoneDao;

    @Mock
    private ZoneDAO zoneDAO;

    @BeforeEach
    void BeforeEach() {
        zoneDao = new ZoneDAO();
        zoneDao.setInjectedBean(this.jdbcTemplate);
    }

    /**
     * Test: Get all zones from database
     */
    @Test
    void getZones_ShouldReturnAllZones() {
        try {
            List<Zone> zones = zoneDao.getAll();
            assertEquals(4, zones.size());
        }
        catch (Exception e) {
            fail();
        }

    }

    @Test
    void getAllZoneRequests() {
        List<ZoneDTO> zoneDTOS = Collections.singletonList(new ZoneDTO());

        when(zoneDAO.getAllZoneRequests()).thenReturn(zoneDTOS);
        assertEquals(1, zoneDAO.getAllZoneRequests().size());
        assertSame(zoneDAO.getAllZoneRequests().get(0).getClass(), ZoneDTO.class);
    }

    @Test
    void handleZoneRequest() throws Exception {
        ZoneDTO zoneDTO = new ZoneDTO();
        zoneDTO.setZoneId(1);
        zoneDTO.setZoneTitle("Zone 01");
        zoneDTO.setCourierId(1);
        zoneDTO.setCourierName("Courier 01");

        when(zoneDAO.acceptZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId())).thenReturn(1);
        assertEquals(1, zoneDAO.acceptZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId()));
        when(zoneDAO.rejectZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId())).thenReturn(1);
        assertEquals(1, zoneDAO.acceptZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId()));
    }

    /**
     * Test: Add a new zone to the database
     */
    @Test
    void saveZone_ShouldAddZone() {
        try {
            Zone zone = new Zone(12, "newTestZone");
            zoneDao.save(zone);
            List<Zone> zones = zoneDao.getAll();
            assertEquals(5, zones.size());
        }
        catch (Exception e) {
            fail();
        }
    }

    /**
     * Test: Deletes zone from database
     */
    @Test
    void deleteZone_ShouldDeleteZone() {
        try {
            zoneDao.delete(1);
            List<Zone> zones = zoneDao.getAll();
            assertEquals(3, zones.size());
        }
        catch (Exception e) {
            fail();
        }
    }
}
