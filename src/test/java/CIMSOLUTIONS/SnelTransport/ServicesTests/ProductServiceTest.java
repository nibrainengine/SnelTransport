package CIMSOLUTIONS.SnelTransport.ServicesTests;

import CIMSOLUTIONS.SnelTransport.DAO.ProductDAO;
import CIMSOLUTIONS.SnelTransport.DTO.ProductDTO;
import CIMSOLUTIONS.SnelTransport.Services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    private ProductDAO productDAO;

    private ProductDTO productDTO;

    @BeforeEach
    public void setup(){
        productDTO = getProductDTO();
    }

    @Test
    void testGet() throws Exception {
        List<ProductDTO> productDTOS = Collections.singletonList(productDTO);
        when(productDAO.getAllAvailable()).thenReturn(productDTOS);
        assertEquals(productDTOS, productService.getAll());
    }

    @Test
    void testGetEmptyList() throws Exception {
        assertEquals(Collections.emptyList(), productService.getAll());
    }

    private ProductDTO getProductDTO(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("test name");
        productDTO.setPrice(299.99);
        productDTO.setCategories(Collections.singletonList("category one"));
        productDTO.setQuantity(10);
        return productDTO;
    }
}
