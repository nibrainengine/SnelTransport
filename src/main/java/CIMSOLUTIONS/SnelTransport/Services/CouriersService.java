package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.CouriersDAO;
import CIMSOLUTIONS.SnelTransport.DAO.PackageSizeDAO;
import CIMSOLUTIONS.SnelTransport.Models.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CIMSOLUTIONS.SnelTransport.DTO.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CouriersService {

    private CouriersDAO couriersDAO;
    private PackageSizeDAO packageSizeDAO;

    @Autowired
    public void setInjectedBean(CouriersDAO couriersDAO, PackageSizeDAO packageSizeDAO){
        this.couriersDAO = couriersDAO;
        this.packageSizeDAO = packageSizeDAO;
    }

    /**
     * Gets all couriers in CIMSOLUTIONS.SnelTransport.dto format, ensuring only the id, name and package size are given.
     * @return List<CourierDTO>
     */
    public List<CourierDTO> getAll() {
        List<CourierDTO> courierDTOS = couriersDAO.getAll();
        for (CourierDTO courierDTO : courierDTOS){
            courierDTO.setPackageSize(packageSizeDAO.getPackageSizeCourier(courierDTO.getId()));
        }
        return courierDTOS;
    }

    /**
     * Fuses a Courier and Packagesize Object
     * @param courierId unique identifier of courier
     * @return Single Courier object
     * @throws Exception Exeption message.
     */
    public Courier getCourierInfo(int courierId) throws Exception {
        Courier courier = couriersDAO.getCourierInfo(courierId);
        courier.setPackageSizes(packageSizeDAO.getPackageSizeCourier(courierId));
        return courier;
    }

    /**
     * Fuses a Courier and Packagesize Object
     * @param courier a single courier with an unique identifier
     * @return Single Courier object
     * @throws Exception Exeption message.
     */
    public Courier save(Courier courier) throws Exception {
        try{
            Courier courierObject = couriersDAO.save(courier);
            courier.setPackageSizes(packageSizeDAO.getPackageSizeCourier(courierObject.getId()));
            return courier;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
