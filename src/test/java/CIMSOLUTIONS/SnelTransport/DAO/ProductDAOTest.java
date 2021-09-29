package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.class_objects.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductDAOTest {

    ProductDAO productDAO = Mockito.mock(ProductDAO.class);

    @Test
    void getProduct() {
        when(productDAO.getProduct(0,0,0)).thenReturn(new Product());
        assertTrue(productDAO.getProduct(0,0,0).getClass() == Product.class);
    }
}
