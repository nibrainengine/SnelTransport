package Database.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AzureSQLConnection {

    /**
     * This method makes a connection with the database by reading the authentication from credentials.properties. The connection is for Azure SQL.
     * The returned connection can be used to fetch data from the database. After using the getConnection() method. The connection must be closed (by using a conn.close()
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("credentials.properties")));
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        String url = String.format("jdbc:jtds:sqlserver://sneltransport.database.windows.net:1433/SnelTransportDB;user="+properties.getProperty("username")+";password="+properties.getProperty("password")+";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request");
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }
}
