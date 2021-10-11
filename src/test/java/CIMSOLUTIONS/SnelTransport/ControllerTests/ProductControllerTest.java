package CIMSOLUTIONS.SnelTransport.ControllerTests;

import CIMSOLUTIONS.SnelTransport.DTO.ProductDTO;
import CIMSOLUTIONS.SnelTransport.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGet() throws Exception {
        ProductDTO productDTO = getProductDTO();
        List<ProductDTO> products = Collections.singletonList(productDTO);
        when(productService.getAll()).thenReturn(products);

        this.mockMvc.perform(get("/api/products/available")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(productDTO.getId()))
                .andExpect(jsonPath("$[0].name").value(productDTO.getName()))
                .andExpect(jsonPath("$[0].price").value(productDTO.getPrice()))
                .andExpect(jsonPath("$[0].categories[0]").value(productDTO.getCategories().get(0)))
                .andExpect(jsonPath("$[0].quantity").value(productDTO.getQuantity()));
    }

    @Test
    public void testGetBadRequest() throws Exception {
        when(productService.getAll()).thenThrow(Exception.class);
        this.mockMvc.perform(get("/api/products/available")).andDo(print()).andExpect(status().isBadRequest());
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
