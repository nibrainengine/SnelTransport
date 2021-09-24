package CIMSOLUTIONS.SnelTransport.Zone;

import class_objects.Zone;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IZoneDao {
    Zone save(Zone zone) throws Exception;
    ArrayList<Zone> getAll() throws Exception;
    Zone update(String id, Zone zone);
    Zone delete(Zone zone);
}
