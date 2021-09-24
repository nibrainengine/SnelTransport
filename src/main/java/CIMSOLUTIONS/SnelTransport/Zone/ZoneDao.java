package CIMSOLUTIONS.SnelTransport.Zone;

import class_objects.Zone;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ZoneDao implements IZoneDao {

    /**
     * Add a new zone
     *
     * @param zone zone to add
     * @return Zone
     */
    @Override
    public Zone save(Zone zone) throws Exception {
        try {
            ArrayList<Zone> zones = new ArrayList<>();
            Connection conn = GetConnection.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO Zone (title) VALUES ('" + zone.getZoneTitle() + "')");

            return zone;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Get all zones
     *
     * @return ArrayList<Zone>
     * @throws Exception Can't create database connection
     */
    @Override
    public ArrayList<Zone> getAll() throws Exception {
        ArrayList<Zone> zones = new ArrayList<>();
        Connection conn = GetConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Zone");

        while (rs.next()) {
            Zone zone = new Zone(rs.getInt("id"), rs.getString("title"));
            zones.add(zone);
        }
        rs.close();

        return zones;
    }

    /**
     * Updates existing zone
     *
     * @param id   id of the zone
     * @param zone update zone
     */
    @Override
    public Zone update(String id, Zone zone) {
        //Todo update zone
        return null;
    }

    /**
     * Removes existing zone
     *
     * @param zone zone to remove
     */
    @Override
    public Zone delete(Zone zone) {
        //Todo remove zone
        return null;
    }
}
