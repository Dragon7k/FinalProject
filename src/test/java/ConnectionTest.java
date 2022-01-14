import by.epam.gamestore.connection.ConnectionPool;
import org.junit.jupiter.api.Test;

public class ConnectionTest {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Test
    public void TestConnection(){
        System.out.println(connectionPool.takeConnection());
    }
}
