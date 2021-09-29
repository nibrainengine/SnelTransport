package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.class_objects.Address;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AddressDAOTest {

    AddressDAO addressDAO = Mockito.mock(AddressDAO.class);

    @Test
    void getStartAddress() {
        when(addressDAO.getStartAddress(0,0)).thenReturn(new Address());
        assertTrue(addressDAO.getStartAddress(0,0).getClass() == Address.class);
    }

    @Test
    void getEndAddress() {
        when(addressDAO.getEndAddress(0,0)).thenReturn(new Address());
        assertTrue(addressDAO.getEndAddress(0,0).getClass() == Address.class);
    }

    @Test
    void getDeliveryAddress() {
        when(addressDAO.getDeliveryAddress(0,0,0)).thenReturn(new Address());
        assertTrue(addressDAO.getDeliveryAddress(0,0,0).getClass() == Address.class);
    }
}
