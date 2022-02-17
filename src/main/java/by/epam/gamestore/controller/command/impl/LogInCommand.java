package by.epam.gamestore.controller.command.impl;

import by.epam.gamestore.controller.command.Command;
import by.epam.gamestore.controller.command.CommandResponse;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.exception.ServiceException;
import by.epam.gamestore.service.UserService;
import by.epam.gamestore.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.epam.gamestore.controller.command.ConstantParameter.*;

public class LogInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String passHash = DigestUtils.sha256Hex(password);
        String response;
        Optional<User> userOptional;
        try {
            userOptional = userService.retrieveUserIfExists(passHash, email);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            return new CommandResponse(ERROR, ERROR_STATUS_500);
        }
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            response = new Gson().toJson(user);
            session.setAttribute(USER_STATUS, user.isUserStatus());
        } else {
            return new CommandResponse(ERROR, ERROR_STATUS_500);
        }

        return new CommandResponse(response, OK);
    }
}
