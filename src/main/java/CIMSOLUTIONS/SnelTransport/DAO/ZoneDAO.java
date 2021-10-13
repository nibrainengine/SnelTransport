package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.DTO.ZoneDTO;
import CIMSOLUTIONS.SnelTransport.Models.Zone;
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
            String selectQuery = "select id as id, title as zoneTitle from Zone Where title= '"+ zone.getZoneTitle() + "'";

            jdbcTemplate.execute(query);
            Zone newZone = jdbcTemplate.queryForObject(selectQuery, BeanPropertyRowMapper.newInstance(Zone.class));

            return newZone;
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
     * Function that returns a list of zone requests in CIMSOLUTIONS.SnelTransport.dto format, ensuring only the zoneId,
     * zoneTitle, courierId and courierName are given, by extracting data both from the courierZone, Zone and user
     * tables.
     * @return List<ZoneDTO>
     */
    public List<ZoneDTO> getAllZoneRequests()  {
        String query =  "SELECT DISTINCT Zone.id as zoneId, Zone.title as zoneTitle, courierZone.courierId, " +
                "[user].fullName as courierName " +
                "FROM courierZone, Zone, [user] " +
                "WHERE Zone.id = courierZone.zoneId " +
                "AND courierZone.isApproved = 'false' " +
                "AND courierZone.courierId = [user].userId";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ZoneDTO.class));
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

    /**
     * Function that updates the courierZone table by accepting the requested zone from a specific courier.
     * @param zoneId - The id of the zone that is accepted
     * @param courierId - The id of the courier whose zone is accepted
     * @return 1 if successful, 0 if not
     * @throws Exception if updating the table courierZone failed.
     */
    public int acceptZoneRequest(int zoneId, int courierId) throws Exception {
        try {
            String query =  "UPDATE courierZone " +
                    "SET isApproved = 'true' " +
                    "WHERE zoneId = "+zoneId +
                    "AND courierId = "+courierId;
            return jdbcTemplate.update(query);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Function that updates the courierZone table by removing the requested zone from the specific courier from the
     * table.
     * @param zoneId - The id of the zone that is rejected
     * @param courierId - The id of the courier whose zone is rejected
     * @return 1 if successful, 0 if not
     * @throws Exception if updating the table courierZone failed.
     */
    public int rejectZoneRequest(int zoneId, int courierId) throws Exception {
        try {
            String query = "DELETE FROM courierZone WHERE zoneId = " + zoneId +" AND courierId = "+courierId;
            return jdbcTemplate.update(query);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
