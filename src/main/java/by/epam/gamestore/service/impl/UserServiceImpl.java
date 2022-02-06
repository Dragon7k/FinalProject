package by.epam.gamestore.service.impl;

import by.epam.gamestore.dao.UserDao;
import by.epam.gamestore.dao.impl.UserDaoImpl;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.entity.UserRole;
import by.epam.gamestore.exception.DaoException;
import by.epam.gamestore.exception.ServiceException;
import by.epam.gamestore.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private UserDao userDao = UserDaoImpl.getInstance();
    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User signUp(String login, String password, String email, UserRole role, boolean status, String firstName, String lastName, boolean archived) throws ServiceException {
        String passHash = DigestUtils.sha256Hex(password);
        User user = new User.UserBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setLogin(login)
                .setPassword(passHash)
                .setRole(UserRole.USER)
                .setStatus(false)
                .setArchived(false)
                .buildUser();
        try {
            userDao.addUser(user);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "can't add to database");
            throw new ServiceException();
        }
        return user;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> userList;
        try {
            userList = userDao.findAllUser();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Failed at UserServiceImpl at method findAll");
            throw new ServiceException();
        }
        return userList;
    }
}
