package CIMSOLUTIONS.SnelTransport.DAO;

import class_objects.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZoneDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Add a new zone
     *
     * @param zone zone to add
     * @return Zone
     */
    public Zone save(Zone zone) throws Exception {
        try{
            String query = "INSERT INTO Zone (title) VALUES ('" + zone.getZoneTitle() + "')";
            jdbcTemplate.execute(query);

            return zone;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Get all zones
     *
     * @return List<Zone>
     * @throws Exception Can't create database connection
     */
    public List<Zone> getAll() throws Exception {
        try{
            String query = "select id as id, title as zoneTitle from Zone";
            List<Zone> zones;
            zones = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Zone.class));

            return zones;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Updates existing zone
     *
     * @param id   id of the zone
     * @param zone update zone
     */
    public Zone update(String id, Zone zone) {
        //Todo update zone
        return null;
    }

    /**
     * Deletes requested zone
     * @param id id of zone to delete
     * @throws Exception Can't remove zone
     */
    public void delete(int id) throws Exception {
        try{
            String query = "delete from zone where id = " + id;
            jdbcTemplate.update(query);
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
