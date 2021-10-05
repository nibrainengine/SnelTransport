package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.CourierAvailabilityDAO;
import CIMSOLUTIONS.SnelTransport.DAO.ProductDAO;
import CIMSOLUTIONS.SnelTransport.DTO.ProductDTO;
import CIMSOLUTIONS.SnelTransport.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductDAO productDAO;

    @Autowired
    public void setInjectedBean(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<ProductDTO> getAll() throws Exception {
        return productDAO.getAll();
    }
}
