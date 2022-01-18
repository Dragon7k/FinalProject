package by.epam.gamestore.dao;

import by.epam.gamestore.entity.User;
import by.epam.gamestore.entity.UserStatus;
import by.epam.gamestore.exception.DaoException;
import by.epam.gamestore.exception.DatabaseConnectionException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAllUser() throws DaoException;
    Optional<User> findUserById(long id) throws DatabaseConnectionException;
    boolean IsUserExistByEmail(String email);
    boolean createUser(User user);
    boolean deleteUser(long id);
    boolean blockUnblockUser(long id, UserStatus userStatus);


}
