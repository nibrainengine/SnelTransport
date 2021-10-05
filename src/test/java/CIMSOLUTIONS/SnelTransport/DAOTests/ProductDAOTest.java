package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.ProductDAO;
import CIMSOLUTIONS.SnelTransport.DTO.ProductDTO;
import CIMSOLUTIONS.SnelTransport.Models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@JdbcTest
@Sql({"classpath:schema.sql", "classpath:test-data.sql"})
public class ProductDAOTest {

    ProductDAO productDAO = Mockito.mock(ProductDAO.class);
    ProductDAO productDAOWithJdbc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void BeforeEach() {
        productDAOWithJdbc = new ProductDAO();
        productDAOWithJdbc.setInjectedBean(this.jdbcTemplate);
    }


    @Test
    void getProduct() throws Exception {
        when(productDAO.getProduct(0,0,0)).thenReturn(new Product());
        assertSame(productDAO.getProduct(0, 0, 0).getClass(), Product.class);
    }
    @Test
    void getProductDate() throws Exception {
        when(productDAO.getProductWithDate(0,0)).thenReturn(new Product());
        assertSame(productDAO.getProductWithDate(0, 0).getClass(), Product.class);
    }

    @Test
    void getAll() {
        try {
            List<ProductDTO> products = productDAOWithJdbc.getAll();
            assertEquals(1, products.size());
        }
        catch (Exception e) {
            fail();
        }
    }
}
