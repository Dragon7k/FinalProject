package by.epam.gamestore.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandResponse execute(HttpServletRequest request);
}
