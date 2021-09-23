package CIMSOLUTIONS.SnelTransport.Zone;

import class_objects.Zone;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ZoneDao implements IZoneDao {
    @Override
    public Zone save(Zone zone) {
        return null;
    }

    @Override
    public ArrayList<Zone> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Zone> zones = new ArrayList<>();
        Connection conn = GetConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from gebruiker");

        while (rs.next()) {
            Zone zone = new Zone(rs.getInt("id"), rs.getString("title"));
            zones.add(zone);
        }
        rs.close();

        return zones;
    }

    @Override
    public Zone update(String id, Zone zone) {
        return null;
    }

    @Override
    public Zone delete(Zone zone) {
        return null;
    }
}
