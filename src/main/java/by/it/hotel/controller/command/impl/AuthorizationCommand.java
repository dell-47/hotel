package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.entity.User;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import by.it.hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static by.it.hotel.controller.command.impl.CommandConstants.*;


public class AuthorizationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AuthorizationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        User user = null;
        String page = INDEX_PAGE;

        try {
            user = userService.authorization(login, password);
        } catch (ServiceException e) {
            logger.error(e);
        }

        if (user == null) {
            request.setAttribute("error", LOGIN_ERROR_MESSAGE);
            page = LOGIN_PAGE;
        } else {
            request.getSession().setAttribute("user", user);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);

    }
}
