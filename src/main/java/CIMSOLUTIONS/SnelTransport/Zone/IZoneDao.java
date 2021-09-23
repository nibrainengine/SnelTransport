package CIMSOLUTIONS.SnelTransport.Zone;

import class_objects.Zone;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IZoneDao {
    Zone save(Zone zone);
    ArrayList<Zone> getAll() throws SQLException, ClassNotFoundException;
    Zone update(String id, Zone zone);
    Zone delete(Zone zone);
}
