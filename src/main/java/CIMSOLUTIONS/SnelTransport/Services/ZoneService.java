package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.ZoneDAO;
import class_objects.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService {

    @Autowired
    ZoneDAO zoneDao;

    /**
     * Add a new zone
     * @param zone to add a new zone
     * @return Zone
     * @throws Exception can't create new zone
     */
    public Zone save(Zone zone) throws Exception {
        try{
            Zone addedZone = zoneDao.save(zone);
            return addedZone;
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
            List<Zone> zones = zoneDao.getAll();
            return zones;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Deletes requested zone
     * @param id id of zone to delete
     * @throws Exception Can't remove zone
     */
    public void delete(int id) throws Exception {
        try{
            zoneDao.delete(id);
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
