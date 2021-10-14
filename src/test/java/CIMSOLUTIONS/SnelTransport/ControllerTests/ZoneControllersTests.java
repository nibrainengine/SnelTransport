package CIMSOLUTIONS.SnelTransport.ControllerTests;

import CIMSOLUTIONS.SnelTransport.DTO.ZoneDTO;
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
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    private ZoneDTO zoneDTO;

    @BeforeEach
    public void setup(){
        zone = new Zone(0, "testZone");
        zones = new ArrayList<>();
        zones.add(zone);
        zoneDTO = getZoneDTO();
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

    @Test
    public void testGetAllZoneRequests() throws Exception {
        List<ZoneDTO> zoneDTOS = Collections.singletonList(zoneDTO);
        when(zoneService.getAllZoneRequests()).thenReturn(zoneDTOS);

        this.mockMvc.perform(get("/api/zone/requests/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].zoneId").value(zoneDTO.getZoneId()))
                .andExpect(jsonPath("$[0].zoneTitle").value(zoneDTO.getZoneTitle()))
                .andExpect(jsonPath("$[0].courierId").value(zoneDTO.getCourierId()))
                .andExpect(jsonPath("$[0].courierName").value(zoneDTO.getCourierName()));
    }

    @Test
    public void testHandleZoneRequest() throws Exception {
        when(zoneService.handleZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId(), true)).thenReturn(1);
        this.mockMvc.perform(post("/api/zone/requests/"+zoneDTO.getZoneId()+"/"+zoneDTO.getCourierId())
                        .contentType("application/json").content(objectMapper.writeValueAsString(true)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testHandleZoneRequestBadPost() throws Exception {
        when(zoneService.handleZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId(), true)).thenThrow(Exception.class);
        this.mockMvc.perform(post("/api/zone/requests/"+zoneDTO.getZoneId()+"/"+zoneDTO.getCourierId()))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testHandleZoneRequestWrongBody() throws Exception {
        when(zoneService.handleZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId(), true)).thenReturn(1);
        this.mockMvc.perform(post("/api/zone/requests/"+zoneDTO.getZoneId()+"/"+zoneDTO.getCourierId())
                        .contentType("application/json").content(objectMapper.writeValueAsString("wrong")))
                .andDo(print()).andExpect(status().isBadRequest());
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

    private ZoneDTO getZoneDTO(){
        ZoneDTO zoneDTO = new ZoneDTO();
        zoneDTO.setZoneId(1);
        zoneDTO.setZoneTitle("Zone 01");
        zoneDTO.setCourierId(1);
        zoneDTO.setCourierName("Courier 01");
        return zoneDTO;
    }
}
