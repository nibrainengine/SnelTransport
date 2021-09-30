package Database.DAOImplementation;

import Database.DAO.RouteDAO;
import class_objects.Route;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RouteDAOImplTest {

    RouteDAO routeDAO = Mockito.mock(RouteDAOImpl.class);

    @Test
    void getRoutesTest() throws SQLException, IOException, ClassNotFoundException {
        ArrayList routes = new ArrayList<Route>();
        routes.add(new Route());
        when(routeDAO.getRoutes(1)).thenReturn(routes);
        assertEquals(routeDAO.getRoutes(1).size(), 1);
    }
}
