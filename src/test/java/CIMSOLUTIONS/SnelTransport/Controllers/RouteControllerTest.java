package CIMSOLUTIONS.SnelTransport.Controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RouteControllerTest {

    RouteController routeController = Mockito.mock(RouteController.class);

    @Test
    void getAllRoutesCourier() throws Exception {
        when(routeController.getAllRoutesCourier(1,1)).thenReturn("[{\"endAdress\":{\"zipcode\":\"3438EW\",\"country\":\"The Netherlands\",\"city\":\"Nieuwegein\",\"housenumber\":\"1\",\"street\":\"Symfonielaan\",\"latitude\":5,\"id\":2,\"longitude\":52},\"startAdress\":{\"zipcode\":\"3971KA\",\"country\":\"The Netherlands\",\"city\":\"Zeist\",\"housenumber\":\"23A\",\"street\":\"Hoofdstraat\",\"latitude\":5,\"id\":1,\"longitude\":52},\"index\":1,\"id\":1,\"orderItems\":[{\"product\":{\"size\":\"klein\",\"price\":1300,\"name\":\"Macbook Pro 2021 A7 Chip\",\"id\":1,\"category\":\"Laptop\"},\"eta\":{\"nanos\":0},\"quantity\":1,\"price\":1300,\"orderStatus\":\"in progress\",\"id\":1,\"deliveryAdress\":{\"zipcode\":\"3438EW\",\"country\":\"The Netherlands\",\"city\":\"Nieuwegein\",\"housenumber\":\"1\",\"street\":\"Symfonielaan\",\"latitude\":5,\"id\":2,\"longitude\":52}},{\"product\":{\"size\":\"klein\",\"price\":1300,\"name\":\"Macbook Pro 2021 A7 Chip\",\"id\":1,\"category\":\"Laptop\"},\"eta\":{\"nanos\":0},\"quantity\":1,\"price\":1000,\"orderStatus\":\"in progress\",\"id\":2,\"deliveryAdress\":{\"zipcode\":\"3438EW\",\"country\":\"The Netherlands\",\"city\":\"Nieuwegein\",\"housenumber\":\"1\",\"street\":\"Symfonielaan\",\"latitude\":5,\"id\":2,\"longitude\":52}}]}]");
        assertTrue(routeController.getAllRoutesCourier(1,1).contains("endAdress") && routeController.getAllRoutesCourier(1,1).contains("index"));
    }

    @Test
    void getAllRoutes() throws Exception {
        when(routeController.getAllRoutes("2021-01-01")).thenReturn("[{\"endAdress\":{\"zipcode\":\"3438EW\",\"country\":\"The Netherlands\",\"city\":\"Nieuwegein\",\"housenumber\":\"1\",\"street\":\"Symfonielaan\",\"latitude\":5,\"id\":2,\"longitude\":52},\"startAdress\":{\"zipcode\":\"3971KA\",\"country\":\"The Netherlands\",\"city\":\"Zeist\",\"housenumber\":\"23A\",\"street\":\"Hoofdstraat\",\"latitude\":5,\"id\":1,\"longitude\":52},\"index\":1,\"id\":1,\"orderItems\":[{\"product\":{\"size\":\"klein\",\"price\":1300,\"name\":\"Macbook Pro 2021 A7 Chip\",\"id\":1,\"category\":\"Laptop\"},\"eta\":{\"nanos\":0},\"quantity\":1,\"price\":1300,\"orderStatus\":\"in progress\",\"id\":1,\"deliveryAdress\":{\"zipcode\":\"3438EW\",\"country\":\"The Netherlands\",\"city\":\"Nieuwegein\",\"housenumber\":\"1\",\"street\":\"Symfonielaan\",\"latitude\":5,\"id\":2,\"longitude\":52}},{\"product\":{\"size\":\"klein\",\"price\":1300,\"name\":\"Macbook Pro 2021 A7 Chip\",\"id\":1,\"category\":\"Laptop\"},\"eta\":{\"nanos\":0},\"quantity\":1,\"price\":1000,\"orderStatus\":\"in progress\",\"id\":2,\"deliveryAdress\":{\"zipcode\":\"3438EW\",\"country\":\"The Netherlands\",\"city\":\"Nieuwegein\",\"housenumber\":\"1\",\"street\":\"Symfonielaan\",\"latitude\":5,\"id\":2,\"longitude\":52}}]}]");
        assertTrue(routeController.getAllRoutes("2021-01-01").contains("endAdress") && routeController.getAllRoutes("2021-01-01").contains("index"));
    }
}
