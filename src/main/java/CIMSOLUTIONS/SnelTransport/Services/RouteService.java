package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.AddressDAO;
import CIMSOLUTIONS.SnelTransport.DAO.OrderItemDAO;
import CIMSOLUTIONS.SnelTransport.DAO.ProductDAO;
import CIMSOLUTIONS.SnelTransport.DAO.RouteDAO;
import CIMSOLUTIONS.SnelTransport.class_objects.OrderItem;
import CIMSOLUTIONS.SnelTransport.class_objects.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private RouteDAO routeDAO;
    private ProductDAO productDAO;
    private AddressDAO addressDAO;
    private OrderItemDAO orderItemDAO;

    /**
     * initializing the DAO's in this service class
     * @param routeDAO
     * @param productDAO
     * @param addressDAO
     * @param orderItemDAO
     */
    @Autowired
    public void setInjectionBean(RouteDAO routeDAO, ProductDAO productDAO, AddressDAO addressDAO, OrderItemDAO orderItemDAO){
        this.routeDAO = routeDAO;
        this.addressDAO = addressDAO;
        this.orderItemDAO = orderItemDAO;
        this.productDAO = productDAO;
    }

    /**
     * This function creates a full route object. Firstly the variable 'routes' contains a list of routes where the startAddress,
     * endAddress and orderItems are null for each route. There variables are given values in the for each route loop.
     * This function returns a list of the route object without any empty variables.
     * @param courierId
     * @param scheduleId
     * @return
     */
    public List<Route> get(int courierId, int scheduleId) throws Exception {
        List<Route> routes = routeDAO.get(courierId, scheduleId);
        for (Route route : routes){
            route.setStartAddress(addressDAO.getStartAddress(courierId, scheduleId));
            route.setEndAddress(addressDAO.getEndAddress(courierId, scheduleId));
            route.setDeliveryAddress(addressDAO.getDeliveryAddress(route.getIndex(),courierId,scheduleId));
            route.setOrderItems(orderItemDAO.getOrderItems(route.getIndex(),courierId,scheduleId));
            for(OrderItem orderItem : route.getOrderItems()){
                orderItem.setProduct(productDAO.getProduct(route.getIndex(),courierId,scheduleId));
                orderItem.setDeliveryAddress(addressDAO.getDeliveryAddress(route.getIndex(),courierId,scheduleId));
            }
        }
        return routes;
    }

    /**
     * This function creates a full route object. Firstly the variable 'routes' contains a list of routes where the startAddress,
     * endAddress and orderItems are null for each route. There variables are given values in the for each route loop.
     * This function returns a list of the route object without any empty variables.
     * @param date
     * @return
     */
    public List<Route> getWithDate(String date) throws Exception {
        try {
            List<Route> routes = routeDAO.getWithDate(date);
            for (Route route : routes) {
                route.setStartAddress(addressDAO.getStartAddressWithDate(route.getId()));
                route.setEndAddress(addressDAO.getEndAddressWithDate(route.getId()));
                route.setDeliveryAddress(addressDAO.getDeliveryAddressWithDate(route.getIndex(), route.getId()));
                route.setOrderItems(orderItemDAO.getOrderItemsWithDate(route.getIndex(), route.getId()));
                for (OrderItem orderItem : route.getOrderItems()) {
                    orderItem.setProduct(productDAO.getProductWithDate(orderItem.getId(), route.getId()));
                }
            }
            return routes;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
