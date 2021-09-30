package CIMSOLUTIONS.SnelTransport.PickUp;

import CIMSOLUTIONS.SnelTransport.Controllers.PickupDataController;
import class_objects.Address;
import class_objects.PickUpHub;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PickupDataControllerTests {

    PickupDataController pickupDataController = Mockito.mock(PickupDataController.class);

    @Test
    void postBadPickup(){
        PickUpHub pickUpHub = new PickUpHub( new Address(), null, null);
        when(pickupDataController.addPickupAPI(pickUpHub)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null), pickupDataController.addPickupAPI(pickUpHub));
    }

    @Test
    void getPickupData(){
        when(pickupDataController.getPickupData()).thenReturn(ResponseEntity.status(HttpStatus.ACCEPTED).body(null));
        assertEquals(ResponseEntity.status(HttpStatus.ACCEPTED).body(null), pickupDataController.getPickupData());
    }
}
