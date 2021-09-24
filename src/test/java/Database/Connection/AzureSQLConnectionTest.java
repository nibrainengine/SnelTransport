package Database.Connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AzureSQLConnectionTest {

    @Test
    void getConnectionTest() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = AzureSQLConnection.getConnection();
        assertEquals(false,conn.isClosed());
        conn.close();
        assertEquals(true,conn.isClosed());
    }
}
