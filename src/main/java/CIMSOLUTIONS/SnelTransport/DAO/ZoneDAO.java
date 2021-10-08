package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.Models.Zone;
import CIMSOLUTIONS.SnelTransport.Models.ZonePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ZoneDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Add a new zone with zonePoints
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

            //insert query for zonePoints
            String zonePointsQuery = "INSERT INTO zonePoint (zoneId, latitude, longitude) VALUES('"+ newZone.getId() +"', ?, ?)";

            //insert array of zonePoints in database
            jdbcTemplate.batchUpdate(zonePointsQuery, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    ZonePoint zonePoint = zone.getZonePoints().get(i);
                    preparedStatement.setDouble(1, zonePoint.getLatitude());
                    preparedStatement.setDouble(2, zonePoint.getLongitude());
                }

                @Override
                public int getBatchSize() {
                    return zone.getZonePoints().size();
                }
            });


            return zone;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Get all zones from database
     * Creates a hashmap to store zones and link zonepoints to the corresponding zone.
     * @return List<Zone>
     * @throws Exception Can't create database connection
     */
    public List<Zone> getAll() throws Exception {
        try{
            String query = "SELECT z.id, z.title, zp.zoneId, zp.latitude, zp.longitude " +
                    "FROM zonePoint zp " +
                    "INNER JOIN zone z on zp.zoneId = z.id";

            Map<Integer, Zone> zoneMap = new HashMap<>();

            jdbcTemplate.query(query, rs -> {
                int id = rs.getInt(1);
                String zoneTitle = rs.getString(2);
                int zoneId = rs.getInt(3);
                double latitude = rs.getDouble(4);
                double longitude = rs.getDouble(5);

                ZonePoint zonePoint = new ZonePoint(zoneId, latitude, longitude);

                getZone(zoneMap, id, zoneTitle).addZonePoint(zonePoint);
            });
            List<Zone> zones = Collections.list(Collections.enumeration(zoneMap.values()));
            return zones;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Checks if zone exists in zone hashmap by id.
     * A new Zone object will be added to the hashmap if it doesn't exist.
     * @param zoneMap a hashmap of zones
     * @param id the id of zone
     * @param zoneTitle the name of zone
     * @return zone object
     */
    private Zone getZone(Map<Integer, Zone> zoneMap, int id, String zoneTitle) {
        Zone zone = zoneMap.get(id);
        if (zone == null) {
            zone = new Zone(id, zoneTitle);
            zoneMap.put(id, zone);
        }
        return zone;
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
