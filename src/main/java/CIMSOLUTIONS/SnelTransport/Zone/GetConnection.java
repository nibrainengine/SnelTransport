package CIMSOLUTIONS.SnelTransport.Zone;

import java.sql.*;

public class GetConnection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        String url = String.format("jdbc:sqlserver://sneltransport.database.windows.net:1433;database=SnelTransportDB");
        return DriverManager.getConnection(url);
    }
}
