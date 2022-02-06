package by.epam.gamestore.controller;

import by.epam.gamestore.controller.command.Command;
import by.epam.gamestore.controller.command.CommandFactory;
import by.epam.gamestore.controller.command.CommandResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MainServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        processCommand(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        processCommand(request, response);

    }

    private void processCommand(HttpServletRequest req, HttpServletResponse res) {
        String commandName = req.getParameter(COMMAND);
        Command clientCommand = CommandFactory.getInstance().getCommand(commandName);
        CommandResponse commandResponse = clientCommand.execute(req);
        try {
            res.getWriter().write(commandResponse.getJson());
        } catch (IOException e) {
            logger.log(Level.ERROR, "can't response");
            res.setStatus(500);
        }

    }

}
