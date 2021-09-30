package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.class_objects.Address;
import CIMSOLUTIONS.SnelTransport.class_objects.Route;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RouteServiceTest {

    RouteService routeService = Mockito.mock(RouteService.class);

    @Test
    void get() throws Exception {
        List<Route> routes = new ArrayList<>();
        routes.add(new Route());
        routes.get(0).setEndAddress(new Address());
        routes.get(0).setStartAddress(new Address());
        when(routeService.get(0,0)).thenReturn(routes);
        assertTrue(routeService.get(0,0).get(0).getEndAddress().getClass() == Address.class);
    }

    @Test
    void getAllByDate() throws Exception {
        List<Route> routes = new ArrayList<>();
        routes.add(new Route());
        routes.get(0).setEndAddress(new Address());
        routes.get(0).setStartAddress(new Address());
        when(routeService.getWithDate("2021-01-01")).thenReturn(routes);
        assertTrue(routeService.getWithDate("2021-01-01").get(0).getEndAddress().getClass() == Address.class);
    }
}
