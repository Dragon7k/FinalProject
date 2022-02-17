package by.epam.gamestore.controller.command.impl;

import by.epam.gamestore.controller.command.Command;
import by.epam.gamestore.controller.command.CommandResponse;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.exception.ServiceException;
import by.epam.gamestore.service.UserService;
import by.epam.gamestore.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.epam.gamestore.controller.command.ConstantParameter.*;

public class ViewUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        UserService userService = UserServiceImpl.getInstance();
        List<User> userList;
        String response;
        try {
            userList = userService.findAll();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            return new CommandResponse(ERROR, ERROR_STATUS_500);
        }
        response = new Gson().toJson(userList);

        return new CommandResponse(response, OK);
    }
}
