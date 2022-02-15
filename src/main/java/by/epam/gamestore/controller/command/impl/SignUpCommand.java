package by.epam.gamestore.controller.command.impl;

import by.epam.gamestore.controller.command.Command;
import by.epam.gamestore.controller.command.CommandResponse;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.entity.UserRole;
import by.epam.gamestore.exception.ServiceException;
import by.epam.gamestore.service.UserService;
import by.epam.gamestore.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.gamestore.controller.command.ConstantParameter.*;

public class SignUpCommand implements Command {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        UserService userService = UserServiceImpl.getInstance();
        User user = null;
        String response;
        try {
            user = new Gson().fromJson(request.getReader(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(user!=null){
            try {
                userService.signUp(user);
            } catch (ServiceException e) {
                return new CommandResponse(ERROR, ERROR_STATUS_500);
            }
            response = new Gson().toJson(user);
            return new CommandResponse(response,OK);
        }
        return new CommandResponse(ERROR,ERROR_STATUS_500);
    }
}
