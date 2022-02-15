package by.epam.gamestore.service;

import by.epam.gamestore.entity.User;
import by.epam.gamestore.entity.UserRole;
import by.epam.gamestore.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User signUp(User user) throws ServiceException;

    List<User> findAll() throws ServiceException;
    Optional<User> retrieveUserIfExists(String password, String email) throws ServiceException;

}
