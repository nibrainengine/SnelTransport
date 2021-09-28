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

    @Autowired
    public void setInjectionBean(RouteDAO routeDAO, ProductDAO productDAO, AddressDAO addressDAO, OrderItemDAO orderItemDAO){
        this.routeDAO = routeDAO;
        this.addressDAO = addressDAO;
        this.orderItemDAO = orderItemDAO;
        this.productDAO = productDAO;
    }

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
