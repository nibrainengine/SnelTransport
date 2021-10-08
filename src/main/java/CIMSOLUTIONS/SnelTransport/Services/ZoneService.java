package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.ZoneDAO;
import CIMSOLUTIONS.SnelTransport.Models.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
     * @throws Exception ZonePoints are empty
     */
    public Zone save(Zone zone) throws Exception {
        if(zone.getZonePoints().size() < 4){
            throw new Exception();
        }
        return zoneDao.save(zone);
    }

    /**
     * Get all zones
     *
     * @return List<Zone>
     * @throws Exception Can't create database connection
     */
    public List<Zone> getAll() throws Exception {
        try{
            return zoneDao.getAll();
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
