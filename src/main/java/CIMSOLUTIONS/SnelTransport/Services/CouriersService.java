package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.CouriersDAO;
import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouriersService {

    private CouriersDAO couriersDAO;

    @Autowired
    public void setInjectedBean(CouriersDAO couriersDAO){
        this.couriersDAO = couriersDAO;
    }

    /**
     * Gets all couriers in dto format, ensuring only the id and name are given.
     * @return List<CourierDTO>
     */
    public List<CourierDTO> getAll() {
        return couriersDAO.getAll();
    }
}
