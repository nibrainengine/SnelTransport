package Database.Connection;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AzureSQLConnectionTest {

    @Test
    void getConnectionTest() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = AzureSQLConnection.getConnection();
        assertEquals(false,conn.isClosed());
        conn.close();
        assertEquals(true,conn.isClosed());
    }
}
