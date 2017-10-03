import com.rentalcar.dao.jdbc.DBConnection;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DBConnectionTest {

    @Test
    public void testConnection(){
        Connection connection = DBConnection.getConnection();
        assertNotNull(connection);
    }
}