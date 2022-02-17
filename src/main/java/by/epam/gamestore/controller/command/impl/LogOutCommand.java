package by.epam.gamestore.controller.command.impl;

import by.epam.gamestore.controller.command.Command;
import by.epam.gamestore.controller.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.gamestore.controller.command.ConstantParameter.CLEAR_SESSION;
import static by.epam.gamestore.controller.command.ConstantParameter.OK;

public class LogOutCommand implements Command {
    @Override
    public CommandResponse execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new CommandResponse(CLEAR_SESSION, OK);
    }
}
