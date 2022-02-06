import by.epam.gamestore.connection.ConnectionFactory;
import by.epam.gamestore.connection.ConnectionPool;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.entity.UserRole;
import by.epam.gamestore.exception.DatabaseConnectionException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String sqlAdd = "INSERT INTO usr (user_id, firstName, lastName, email, login, pass, roles) VALUES (?,?,?,?,?,?,?)";

    @Test
    public void TestConnection() throws DatabaseConnectionException {
        int result;
        PreparedStatement preparedStatement = null;

        User user = new User();
        user.setEmail("asd@mail.ru");
        user.setFirstName("vitalya");
        user.setLastName("ivliev");
        user.setPassword("1111");
        user.setLogin("Batyanya");
        user.setRole(UserRole.USER);

        try (Connection connection = ConnectionFactory.createConnection()) {
            preparedStatement = connection.prepareStatement(sqlAdd);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, String.valueOf(user.getRole()));
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
            throw new DatabaseConnectionException();
        }
    }
}
