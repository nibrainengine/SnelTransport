package CIMSOLUTIONS.SnelTransport.ServicesTests;

import CIMSOLUTIONS.SnelTransport.DAO.ZoneDAO;
import CIMSOLUTIONS.SnelTransport.DTO.ZoneDTO;
import CIMSOLUTIONS.SnelTransport.Services.ZoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ZoneServiceTest {

    @InjectMocks
    ZoneService zoneService;

    @Mock
    private ZoneDAO zoneDAO;

    private ZoneDTO zoneDTO;

    @BeforeEach
    public void setup(){
        zoneDTO = getZoneDTO();
    }

    @Test
    void testGetAllZoneRequests() throws Exception {
        List<ZoneDTO> zoneDTOS = Collections.singletonList(zoneDTO);
        when(zoneDAO.getAllZoneRequests()).thenReturn(zoneDTOS);
        assertEquals(zoneDTOS,zoneService.getAllZoneRequests());
    }

    @Test
    void testGetEmptyZoneRequestsList() {
        assertEquals(Collections.emptyList(),zoneService.getAllZoneRequests());
    }

    @Test
    void testHandleZoneRequest() throws Exception {
        when(zoneDAO.acceptZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId())).thenReturn(1);
        assertEquals(1,zoneService.handleZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId(), true));
        when(zoneDAO.rejectZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId())).thenReturn(1);
        assertEquals(1,zoneService.handleZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId(), false));
    }

    @Test
    void testHandleZoneRequestIs0() throws Exception {
        assertEquals(0,zoneService.handleZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId(), true));
        assertEquals(0,zoneService.handleZoneRequest(zoneDTO.getZoneId(), zoneDTO.getCourierId(), false));
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
