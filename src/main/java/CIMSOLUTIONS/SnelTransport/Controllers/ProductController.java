package CIMSOLUTIONS.SnelTransport.Controllers;

import CIMSOLUTIONS.SnelTransport.DTO.ProductDTO;
import CIMSOLUTIONS.SnelTransport.Models.AvailablePeriod;
import CIMSOLUTIONS.SnelTransport.Models.Product;
import CIMSOLUTIONS.SnelTransport.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setInjectedBean(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets all Products from database that are currently available for customers.
     * @return ResponseEntity<List<ProductDTO>> The productDTO object is a simple version of the Product Object with
     * only information the customer has to see. Quantity is added to the ProductDTO.
     */
    @GetMapping("/available")
    public ResponseEntity<List<ProductDTO>> getAll() {
        try {
            return ResponseEntity.ok(productService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
