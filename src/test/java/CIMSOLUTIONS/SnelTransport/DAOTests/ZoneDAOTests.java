package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.ZoneDAO;
import CIMSOLUTIONS.SnelTransport.class_objects.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Sql({"classpath:schema.sql", "classpath:test-data.sql"})
public class ZoneDAOTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ZoneDAO zoneDao;

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
