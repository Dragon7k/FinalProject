package by.epam.gamestore.service;

import by.epam.gamestore.entity.User;
import by.epam.gamestore.entity.UserRole;
import by.epam.gamestore.exception.ServiceException;

import java.util.List;

public interface UserService {
    User signUp(String login, String password, String email, UserRole role,
                boolean status, String firstName, String lastName, boolean archived) throws ServiceException;

    List<User> findAll() throws ServiceException;
}
