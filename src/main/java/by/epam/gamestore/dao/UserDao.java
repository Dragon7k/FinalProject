package by.epam.gamestore.dao;

import by.epam.gamestore.entity.User;
import by.epam.gamestore.exception.DaoException;
import by.epam.gamestore.exception.DatabaseConnectionException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAllUser() throws DaoException;

    Optional<User> findUserById(long id) throws  DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    boolean addUser(User user) throws DaoException;

    boolean deleteUser(long id);

    boolean blockUnblockUser(long id, boolean userStatus);


}
