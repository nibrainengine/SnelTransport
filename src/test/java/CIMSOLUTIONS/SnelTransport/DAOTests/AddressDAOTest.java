package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.AddressDAO;
import CIMSOLUTIONS.SnelTransport.Models.Address;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AddressDAOTest {

    AddressDAO addressDAO = Mockito.mock(AddressDAO.class);

    @Test
    void getStartAddress() throws Exception {
        when(addressDAO.getStartAddress(0,0)).thenReturn(new Address());
        assertSame(addressDAO.getStartAddress(0, 0).getClass(), Address.class);
    }

    @Test
    void getEndAddress() throws Exception {
        when(addressDAO.getEndAddress(0,0)).thenReturn(new Address());
        assertSame(addressDAO.getEndAddress(0, 0).getClass(), Address.class);
    }

    @Test
    void getDeliveryAddressDate() throws Exception {
        when(addressDAO.getDeliveryAddress(0,0,0)).thenReturn(new Address());
        assertSame(addressDAO.getDeliveryAddress(0, 0, 0).getClass(), Address.class);
    }

    @Test
    void getStartAddressDate() throws Exception {
        when(addressDAO.getStartAddressWithDate(1)).thenReturn(new Address());
        assertSame(addressDAO.getStartAddressWithDate(1).getClass(), Address.class);
    }

    @Test
    void getEndAddressDate() throws Exception {
        when(addressDAO.getEndAddressWithDate(1)).thenReturn(new Address());
        assertSame(addressDAO.getEndAddressWithDate(1).getClass(), Address.class);
    }

    @Test
    void getDeliveryAddress() throws Exception {
        when(addressDAO.getDeliveryAddressWithDate(1,1)).thenReturn(new Address());
        assertSame(addressDAO.getDeliveryAddressWithDate(1, 1).getClass(), Address.class);
    }
}
