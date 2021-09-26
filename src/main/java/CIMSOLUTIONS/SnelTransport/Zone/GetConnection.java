package CIMSOLUTIONS.SnelTransport.Zone;

import java.sql.*;

public class GetConnection {

    /**
     * Creates a connection with the database
     * @return Connection
     * @throws Exception Can't create connection with the database
     */
    public static Connection getConnection() throws Exception {
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:sqlserver://sneltransport.database.windows.net:1433;database=SnelTransportDB;user=team-so-21@cimsolutions.nl@sneltransport;password=C!M$OL2021;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request";
            Connection conn = DriverManager.getConnection(url);
            return conn;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
