package CIMSOLUTIONS.SnelTransport.ControllerTests;

import CIMSOLUTIONS.SnelTransport.Models.Address;
import CIMSOLUTIONS.SnelTransport.Models.Route;
import CIMSOLUTIONS.SnelTransport.Services.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Route> route;

    @BeforeEach
    public void setup(){
        route = getRoutes();
    }

    @Test
    void getAllRoutesCourier_ReturnOk() throws Exception{
        try{
            when(routeService.get(1,1)).thenReturn(route);
            this.mockMvc.perform(get("/api/my-route/1/1")).andExpect(status().isOk());
        }
        catch (Exception ex){
            fail();
        }
    }

    @Test
    void getAllRoutesCourier_ReturnBadRequest() throws Exception{
        try{
            when(routeService.get(1,1)).thenThrow(new Exception());
            this.mockMvc.perform(get("/api/my-route/1/1")).andExpect(status().isBadRequest());
        }
        catch (Exception ex){
            fail();
        }
    }

    @Test
    void getAllRoutes_ReturnOk() throws Exception{
        try{
            when(routeService.getWithDate("2021-10-01")).thenReturn(route);
            this.mockMvc.perform(get("/api/routes/2021-10-01")).andExpect(status().isOk());
        }
        catch (Exception ex){
            fail();
        }
    }

    @Test
    void getAllRoutes_ReturnBadRequest() throws Exception{
        try{
            when(routeService.getWithDate("2021-10-01")).thenThrow(new Exception());
            this.mockMvc.perform(get("/api/routes/2021-10-01")).andExpect(status().isBadRequest());
        }
        catch (Exception ex){
            fail();
        }
    }

    private List<Route> getRoutes(){
        List<Route> routeList = new ArrayList<Route>();
        Route route = new Route();
        route.setId(1);
        route.setStartTime(new Date());
        route.setEndTime(new Date());
        route.setIndex(1);
        route.setDeliveryAddress(new Address());
        route.setEndAddress(new Address());
        route.setStartAddress(new Address());
        routeList.add(route);
        return routeList;
    }
}
