package CIMSOLUTIONS.SnelTransport.DAO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RouteDAOTest {

    @Autowired
    RouteDAO routeDAO;

    @Test
    void get() {
        routeDAO.get(1,1);
    }
}
