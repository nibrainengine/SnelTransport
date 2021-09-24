package Database.DAOImplementation;

import Database.Connection.AzureSQLConnection;
import Database.DAO.RouteDAO;
import class_objects.Address;
import class_objects.OrderItem;
import class_objects.Product;
import class_objects.Route;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RouteDAOImpl implements RouteDAO {

    /**
     * This method fetches all routes from the database for a specific courier. The getConnection is called to make a Database connection. The connections reserves a statement
     * by using the methode .createStatement(). The statement is filled and execuded the the executeQuery(); method. The resultset and connection must be closed at the end of the method.
     * @param courierId
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public ArrayList<Route> getRoutes(int courierId, int scheduleId) throws SQLException, IOException, ClassNotFoundException {
        try {
            String query = "select distinct scheduleRoute.courierScheduleId as RouteId, scheduleRoute.indexOrder as orderIndex, address.id as addressid, address.city as city, address.country as country, address.street as street, address.houseNumber as housenumber, address.zipCode as zipcode, address.latitude as latitude, address.longitude as longitude from courier, courierSchedule, scheduleRoute, address where courier.userId = "+courierId+" and courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.startAddress = address.id and courierSchedule.endAddress = address.id and courierSchedule.id = scheduleRoute.courierScheduleId";
            ArrayList<Route> routes = new ArrayList<Route>();
            ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
            Connection conn = AzureSQLConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Route route = new Route(rs.getInt("RouteId"),rs.getInt("orderIndex"), new Address(rs.getInt("addressid"),rs.getString("street"),rs.getString("housenumber"), rs.getString("zipcode"), rs.getString("city"),rs.getString("country"), rs.getDouble("longitude"), rs.getDouble("latitude")), new Address(rs.getInt("addressid"),rs.getString("street"),rs.getString("housenumber"), rs.getString("zipcode"), rs.getString("city"),rs.getString("country"), rs.getDouble("longitude"), rs.getDouble("latitude")));
                Statement stmtOrderItems = conn.createStatement();
                ResultSet rsOrderItems = stmtOrderItems.executeQuery("select address.id as addressid, address.city as city, address.country as country, address.street as street, address.houseNumber as housenumber, address.zipCode as zipcode, address.latitude as latitude, address.longitude as longitude, orderItem.id as orderitemid, orderItem.totalPrice as price, orderItem.eta as eta, orderStatus.name as orderstatus, orderItem.quantity as quantity, product.id as productid, product.name as productname, productSize.name as productsize, product.price as productprice, category.name as category from courier, scheduleRoute, address, orderItem, orderStatus, product, productCategory, category, productSize, courierSchedule where courier.userId = "+courierId+" and courierSchedule.id = "+scheduleId+" and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId and scheduleRoute.indexOrder = "+route.getIndex()+" and scheduleRoute.orderItemsId = orderItem.id and orderItem.orderStatusId = orderStatus.id  and orderItem.productId = product.id and product.id =  productCategory.productId and productCategory.categoryId = category.id and product.sizeId = productSize.id and orderItem.deliveryAddressId = address.id");
                while(rsOrderItems.next()){
                    System.out.println(route.getIndex());
                    OrderItem orderItem = new OrderItem(rsOrderItems.getInt("orderitemid"), rsOrderItems.getInt("quantity"), rsOrderItems.getDouble("price"), rsOrderItems.getDate("eta"), new Product(rsOrderItems.getInt("productid"), rsOrderItems.getString("productname"), rsOrderItems.getDouble("productprice"), rsOrderItems.getString("productsize"), rsOrderItems.getString("category")), rsOrderItems.getString("orderstatus"),new Address(rsOrderItems.getInt("addressid"),rsOrderItems.getString("street"),rsOrderItems.getString("housenumber"), rsOrderItems.getString("zipcode"), rsOrderItems.getString("city"),rsOrderItems.getString("country"), rsOrderItems.getDouble("longitude"), rsOrderItems.getDouble("latitude")));
                    orderItems.add(orderItem);
                }
                route.setOrderItems(orderItems);
                routes.add(route);
                rsOrderItems.close();
            }
            rs.close();
            conn.close();
            return routes;
        }catch(Exception e){
            System.out.println("Error in RouteDaoImpl fetching the routes: " + e);
            return null;
        }
    }
}
