package dao;

import by.epam.gamestore.dao.UserDao;
import by.epam.gamestore.dao.impl.UserDaoImpl;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.entity.UserRole;
import by.epam.gamestore.exception.DaoException;
import by.epam.gamestore.exception.DatabaseConnectionException;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDaoImplTest {

    private UserDao userDao = UserDaoImpl.getInstance();

    @Test
    public void findUserByIdTest() {
        try {
            System.out.println(userDao.findUserById(1).get());
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUser() {
        User user = new User.UserBuilder()
                .setFirstName("Alex")
                .setLastName("Cooler")
                .setEmail("alexCool@mail.ru")
                .setLogin("bublic28")
                .setPassword("rest213")
                .setRole(UserRole.USER)
                .setStatus(false)
                .setArchived(false)
                .buildUser();
        try {
            userDao.addUser(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserByIdTest() {
        userDao.deleteUser(2);
    }

    @Test
    public void BlockUser() {
        userDao.blockUnblockUser(1, true);
    }

    @Test
    public void FindAllUsersTest() {
        try {
            List<User> userList = userDao.findAllUser();
            userList.forEach(System.out::println);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

   /* @Test
    public void IsUserExistByEmail() {
        System.out.println(userDao.IsUserExistByEmail("alexCool@mail.ru"));
    }*/
}
