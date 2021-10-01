package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.RouteDAO;
import CIMSOLUTIONS.SnelTransport.class_objects.Route;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RouteDAOTest {

    RouteDAO routeDAO = Mockito.mock(RouteDAO.class);

    @Test
    void get() throws Exception {
        List<Route> routes = new ArrayList<>();
        routes.add(new Route());
        when(routeDAO.get(0,0)).thenReturn(routes);
        assertTrue(routeDAO.get(0,0).get(0).getClass() == Route.class);
    }

    @Test
    void getDate() throws Exception {
        List<Route> routes = new ArrayList<>();
        routes.add(new Route());
        when(routeDAO.getWithDate("2021-01-01")).thenReturn(routes);
        assertTrue(routeDAO.getWithDate("2021-01-01").get(0).getClass() == Route.class);
    }
}
