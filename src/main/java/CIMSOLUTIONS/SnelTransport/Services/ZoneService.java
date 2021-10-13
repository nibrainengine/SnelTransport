package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.ZoneDAO;
import CIMSOLUTIONS.SnelTransport.DTO.ZoneDTO;
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
     *
     * @param zone to add a new zone
     * @return Zone
     * @throws Exception ZonePoints are empty
     */
    public Zone save(Zone zone) throws Exception {
        if (zone.getZonePoints().size() < 4) {
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
        return zoneDao.getAll();
    }

    /**
     * Gets all zone requests in CIMSOLUTIONS.SnelTransport.dto format, ensuring only the zoneId, zoneTitle, courierId
     * and courierName are given.
     * @return List<ZoneDTO>
     */
    public List<ZoneDTO> getAllZoneRequests() {
        return zoneDao.getAllZoneRequests();
    }

    /**
     * Accepts the zone request of the specified courier.
     * @param zoneId - The id of the zone that is accepted or rejected
     * @param courierId - The id of the courier whose zone is accepted or rejected
     * @param accepted - boolean indicating whether the zone request has been accepted or rejected
     * @return 1 if successful, 0 if not
     * @throws Exception if updating the table courierZone failed.
     */
    public int handleZoneRequest(int zoneId, int courierId, boolean accepted) throws Exception {
        if(accepted){
            return zoneDao.acceptZoneRequest(zoneId, courierId);
        }else{
            return zoneDao.rejectZoneRequest(zoneId, courierId);
        }
    }

    /**
     * Deletes requested zone
     *
     * @param id id of zone to delete
     * @throws Exception Can't remove zone
     */
    public void delete(int id) throws Exception {
        try {
            zoneDao.delete(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
