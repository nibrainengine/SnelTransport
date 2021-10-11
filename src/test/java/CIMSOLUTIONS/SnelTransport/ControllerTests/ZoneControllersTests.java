package CIMSOLUTIONS.SnelTransport.ControllerTests;

import CIMSOLUTIONS.SnelTransport.Services.ZoneService;
import CIMSOLUTIONS.SnelTransport.Models.Zone;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
public class ZoneControllersTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ZoneService zoneService;

    private Zone zone;
    private List<Zone> zones;

    @BeforeEach
    public void setup(){
        zone = new Zone(0, "testZone");
        zones = new ArrayList<>();
        zones.add(zone);
    }

    /**
     * Test: Throws exception on getting a list of zones. Can't retrieve a list of all zones
     */
    @Test
    void getAllZones_ReturnOk(){
        try{
            when(zoneService.getAll()).thenReturn(zones);
            this.mockMvc.perform(get("/api/zone/")).andExpect(status().isOk());
        }
        catch (Exception ex){
            fail();
        }
    }

    /**
     * Test: Retrieve a list of all zones
     */
    @Test
    void getAllZones_ReturnBadRequest(){
        try{
            when(zoneService.getAll()).thenThrow(new Exception());
            this.mockMvc.perform(get("/api/zone/")).andExpect(status().isBadRequest());
        }
        catch (Exception ex){
            fail();
        }
    }

    /**
     * Test: add a new zone
     */
    @Test
    void addZone_ReturnOk(){
        try{
            when(zoneService.save(zone)).thenReturn(zone);
            this.mockMvc.perform(post("/api/zone/").contentType("application/json").content(objectMapper.writeValueAsString(zone))).andExpect(status().isOk());
        }
        catch (Exception ex){
            fail();
        }
    }

    /**
     * Test: throws exception when adding a new zone. Can't add a new zone
     */
    @Test
    void addZone_ReturnBadRequest(){
        try{
            when(zoneService.save(zone)).thenThrow(new Exception());
            this.mockMvc.perform(post("/api/zone/")).andExpect(status().isBadRequest());
        }
        catch (Exception ex){
            fail();
        }
    }

    /**
     * Test: deletes a zone with given id
     */
    @Test
    void deleteZone_ReturnOk(){
        try{
            this.mockMvc.perform(delete("/api/zone/"+ 0)).andExpect(status().isOk());
        }
        catch (Exception ex){
            fail();
        }
    }

    /**
     * Test: Throws Exception on deleting zone, zone can't be deleted
     */
    @Test
    void deleteZone_ReturnBadRequest(){
        try{
            doThrow(new Exception()).when(zoneService).delete(0);
            this.mockMvc.perform(delete("/api/zone/"+ 0)).andExpect(status().isBadRequest());
        }
        catch (Exception ex){
            fail();
        }
    }
}
