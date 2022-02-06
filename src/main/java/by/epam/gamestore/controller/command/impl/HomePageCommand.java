package by.epam.gamestore.controller.command.impl;

import by.epam.gamestore.controller.command.Command;
import by.epam.gamestore.controller.command.CommandResponse;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.exception.ServiceException;
import by.epam.gamestore.service.UserService;
import by.epam.gamestore.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final String keyWord = "user_list";

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        UserService userService = UserServiceImpl.getInstance();
        List<User> userList = null;
        String json = "";
        try {
            userList = userService.findAll();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            json = objectMapper.writeValueAsString(userList);
        } catch (JsonProcessingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        return new CommandResponse(json, keyWord);
    }
}
