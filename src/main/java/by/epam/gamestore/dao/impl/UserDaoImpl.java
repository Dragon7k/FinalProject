package by.epam.gamestore.dao.impl;

import by.epam.gamestore.connection.ConnectionPool;
import by.epam.gamestore.dao.UserDao;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.entity.UserRole;
import by.epam.gamestore.exception.DaoException;
import by.epam.gamestore.exception.DatabaseConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_USER_BY_ID = "select *from usr where user_id =?";
    private static final String SQL_ADD_USER = "insert into usr(firstName,lastName,email,login,pass,roles,user_status,archived) values (?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_USER = "update usr set archived=? where user_id=?";
    private static final String SQL_BLOCK_UNBLOCK_USER = "update usr set user_status =? where user_id=?";
    private static final String SQL_FIND_ALL_USERS = "select *from usr";
    private static final String SQL_FIND_USER_BY_EMAIL = "select *from usr where email =?";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static UserDaoImpl instance;


    private UserDaoImpl() {
    }

    @Override
    public List<User> findAllUser() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = buildUser(resultSet);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "can't select all users");
            throw new DaoException("can't select all users");
        }
        return userList;
    }

    @Override
    public Optional<User> findUserById(long id) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = Optional.of(buildUser(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        }
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    optionalUser=Optional.of(buildUser(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "error in findUserByEmail");
            throw new DaoException(e.getMessage());
        }
        return optionalUser;
    }

    @Override
    public boolean addUser(User user) throws DaoException {
        int result = 0;
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, String.valueOf(user.getRole()));
            preparedStatement.setString(7, String.valueOf(user.isUserStatus() ? 1 : 0));
            preparedStatement.setString(8, String.valueOf(user.isArchived() ? 1 : 0));
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "can't add user");
            throw new DaoException("can't add user");
        }
        return result > 0;
    }

    @Override
    public boolean deleteUser(long id) {
        int result = 0;
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean blockUnblockUser(long id, boolean userStatus) {
        int result = 0;
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_BLOCK_UNBLOCK_USER);
            preparedStatement.setString(1, String.valueOf(userStatus));
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "can't block/unblock user");
        }
        return result > 0;
    }


    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }


    private User buildUser(ResultSet resultSet) throws SQLException {
        return (new User.UserBuilder()
                .setId(resultSet.getLong(1))
                .setFirstName(resultSet.getString(2))
                .setLastName(resultSet.getString(3))
                .setEmail(resultSet.getString(4))
                .setLogin(resultSet.getString(5))
                .setPassword(resultSet.getString(6))
                .setRole(UserRole.valueOf(resultSet.getString(7).toUpperCase()))
                .setStatus(resultSet.getBoolean(8))
                .setArchived(resultSet.getBoolean(9))
                .buildUser());
    }

}
