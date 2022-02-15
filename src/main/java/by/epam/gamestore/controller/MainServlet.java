package by.epam.gamestore.controller;

import by.epam.gamestore.controller.command.Command;
import by.epam.gamestore.controller.command.CommandFactory;
import by.epam.gamestore.controller.command.CommandResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.gamestore.controller.command.ConstantParameter.*;


public class MainServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        processCommand(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        processCommand(request, response);

    }

    private void processCommand(HttpServletRequest req, HttpServletResponse res) {
        String commandName = req.getParameter(COMMAND);
        System.out.println(commandName);
        Command clientCommand = CommandFactory.getInstance().getCommand(commandName);
        CommandResponse result = clientCommand.execute(req);

        if(!result.getResponse().equals(ERROR)) {
            try {
                res.getWriter().write(result.getResponse());
            } catch (IOException e) {
                e.printStackTrace();
                res.setStatus(ERROR_STATUS_404);
            }

        }else{
            res.setStatus(result.getStatus());
        }
    }

}
