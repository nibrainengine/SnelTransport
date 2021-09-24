package Database.DAO;

import class_objects.Route;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RouteDAO {
    /**
     * This method fetches all routes from the database for a specific courier
     * @param courierId
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    ArrayList<Route> getRoutes(int courierId, int scheduleId) throws SQLException, IOException, ClassNotFoundException;
}
