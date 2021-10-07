package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.ProductDAO;
import CIMSOLUTIONS.SnelTransport.DTO.ProductDTO;
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

    /**
     * Gets all Products that are currently available for customers.
     * @return List<ProductDTO> The productDTO object is a simple version of the Product Object with
     * only information the customer has to see. Quantity is added to the ProductDTO.
     */
    public List<ProductDTO> getAll() throws Exception {
        return productDAO.getAllAvailable();
    }
}
