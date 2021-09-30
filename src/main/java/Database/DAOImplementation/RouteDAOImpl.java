package Database.DAOImplementation;

import Database.Connection.AzureSQLConnection;
import Database.DAO.RouteDAO;
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
    public ArrayList<Route> getRoutes(int courierId) throws SQLException, IOException, ClassNotFoundException {
        String query = "select * from courier, courierSchedule, scheduleRoute where courier.userId = "+ courierId +" and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId";
        ArrayList<Route> routes = new ArrayList<Route>();
        Connection conn = AzureSQLConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        rs.close();
        conn.close();
        return null;
    }
}
