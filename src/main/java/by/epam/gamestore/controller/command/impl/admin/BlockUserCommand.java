package by.epam.gamestore.controller.command.impl.admin;

import by.epam.gamestore.controller.command.Command;
import by.epam.gamestore.controller.command.CommandResponse;
import by.epam.gamestore.entity.User;
import by.epam.gamestore.exception.ServiceException;
import by.epam.gamestore.service.UserService;
import by.epam.gamestore.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static by.epam.gamestore.controller.command.ConstantParameter.*;

public class BlockUserCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        Optional<User> optionalUser = Optional.empty();
        int user_id = Integer.parseInt(request.getParameter(USER_ID));
        try {
            optionalUser = userService.findUserById(user_id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.isUserStatus()){
                userService.blockUnblockUser(user.getId(),false);
            }else{
                userService.blockUnblockUser(user.getId(),true);
            }
        }
        return new CommandResponse(USER_STATUS_CHANGE,OK);
    }
}
