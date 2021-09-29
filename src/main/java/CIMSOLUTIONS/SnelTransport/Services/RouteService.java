package CIMSOLUTIONS.SnelTransport.Services;

import CIMSOLUTIONS.SnelTransport.DAO.AddressDAO;
import CIMSOLUTIONS.SnelTransport.DAO.OrderItemDAO;
import CIMSOLUTIONS.SnelTransport.DAO.ProductDAO;
import CIMSOLUTIONS.SnelTransport.DAO.RouteDAO;
import CIMSOLUTIONS.SnelTransport.class_objects.OrderItem;
import CIMSOLUTIONS.SnelTransport.class_objects.Route;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param courierid
     * @param scheduleid
     * @return
     */
    public List<Route> get(int courierid, int scheduleid){
        List<Route> routes = routeDAO.get(courierid, scheduleid);
        for (Route route : routes){
            route.setStartAddress(addressDAO.getStartAddress(courierid, scheduleid));
            route.setEndAddress(addressDAO.getEndAddress(courierid, scheduleid));
            route.setOrderItems(orderItemDAO.getOrderItems(route.getIndex(),courierid,scheduleid));
            for(OrderItem orderItem : route.getOrderItems()){
                orderItem.setProduct(productDAO.getProduct(route.getIndex(),courierid,scheduleid));
                orderItem.setDeliveryAddress(addressDAO.getDeliveryAddress(route.getIndex(),courierid,scheduleid));
            }
        }
        return routes;
    }
}
