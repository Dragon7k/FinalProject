package by.epam.gamestore.dao.impl;

import by.epam.gamestore.connection.ConnectionFactory;
import by.epam.gamestore.dao.UserDao;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.entity.UserRole;
import by.epam.gamestore.entity.UserStatus;
import by.epam.gamestore.exception.DaoException;
import by.epam.gamestore.exception.DatabaseConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_USER_BY_ID = "select *from usr where user_id =?";
    private static final String SQL_ADD_USER = "insert into usr(firstName,lastName,email,login,pass,roles,user_status) values (?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_USER = "delete from usr where user_id=?";
    private static final String SQL_BLOCK_UNBLOCK_USER = "update usr set user_status =? where user_id=?";
    private static final String SQL_FIND_ALL_USERS = "select *from usr";
    private static final String SQL_FIND_USER_BY_EMAIL = "select *from usr where email =?";
    private static UserDaoImpl instance;
    private final Connection connection;

    private UserDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<User> findAllUser() throws DaoException {
        List<User> userList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = getUserInfo(resultSet);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"can't select all users");
            throw new DaoException("can't select all users");
        }
        return userList;
    }

    @Override
    public Optional<User> findUserById(long id) throws DatabaseConnectionException {
        Optional<User> user = Optional.empty();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = Optional.of(getUserInfo(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DatabaseConnectionException("query has failed");
        } finally {
            closeStatement(preparedStatement);
        }
        return user;
    }

    @Override
    public boolean IsUserExistByEmail(String email) {
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        try {
            preparedStatement=connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            preparedStatement.setString(1,email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                if (resultSet.getString(4).equals(email)) {
                    flag = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean createUser(User user) {
        PreparedStatement preparedStatement=null;
        int result=0;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_USER);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getLogin());
            preparedStatement.setString(5,user.getPassword());
            preparedStatement.setString(6, String.valueOf(user.getRole()));
            preparedStatement.setString(7, String.valueOf(user.getUserStatus()));
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeStatement(preparedStatement);
        }
        return result>0;
    }

    @Override
    public boolean deleteUser(long id) {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setLong(1,id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeStatement(preparedStatement);
        }
        return result>0;
    }

    @Override
    public boolean blockUnblockUser(long id, UserStatus userStatus) {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_BLOCK_UNBLOCK_USER);
            preparedStatement.setString(1, String.valueOf(userStatus));
            preparedStatement.setLong(2,id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeStatement(preparedStatement);
        }
        return result>0;
    }


    public static UserDaoImpl getInstance(){
        if(instance==null){
            try {
                instance = new UserDaoImpl(ConnectionFactory.getConnection());
            } catch (SQLException e) {
               logger.log(Level.ERROR,"can't create UserDaoImpl");
            }
        }
        return instance;
    }


    private User getUserInfo(ResultSet resultSet) throws SQLException {
        return (new User.UserBuilder()
                .setId(resultSet.getLong(1))
                .setFirstName(resultSet.getString(2))
                .setLastName(resultSet.getString(3))
                .setEmail(resultSet.getString(4))
                .setLogin(resultSet.getString(5))
                .setPassword(resultSet.getString(6))
                .setRole(UserRole.valueOf(resultSet.getString(7).toUpperCase()))
                .setStatus(UserStatus.valueOf(resultSet.getString(8).toUpperCase()))
                .buildUser());
    }

    private void closeStatement(PreparedStatement preparedStatement){
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
