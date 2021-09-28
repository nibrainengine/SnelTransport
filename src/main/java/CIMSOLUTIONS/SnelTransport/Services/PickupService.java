package CIMSOLUTIONS.SnelTransport.Services;

import class_objects.PickUpHub;

public interface PickupService {
    PickUpHub savePickupHub(PickUpHub pickUpHub);
    PickUpHub getPickupHubById(Integer id);
}
