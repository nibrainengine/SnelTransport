package Database.DAOImplementation;

import Database.DAO.RouteDAO;
import class_objects.Route;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RouteDAOImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    RouteDAO routeDAO = Mockito.mock(RouteDAOImpl.class);

    @Test
    void getRoutesTest() throws SQLException, IOException, ClassNotFoundException {
        ArrayList routes = new ArrayList<Route>();
        routes.add(new Route());
        when(routeDAO.getRoutes(1,1)).thenReturn(routes);
        assertEquals(routeDAO.getRoutes(1,1).size(), 1);
    }

    @Test
    void getRoutesTestCatch() throws SQLException, IOException, ClassNotFoundException {
        expectedException.expect(RuntimeException.class);
        routeDAO.getRoutes(-1,-1);
    }
}
