package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.entity.User;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import by.it.hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.it.hotel.controller.command.impl.CommandConstants.*;


public class AuthCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AuthCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        String login = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String page = INDEX_PAGE;
        try {
            User user = userService.retrieveUser(login);
            if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
                request.setAttribute(LOGIN_ERROR_ATTRIBUTE, LOGIN_ERROR_MESSAGE);
                page = LOGIN_PAGE;
            } else {
                user.setPassword(BLANK_STRING);
                request.getSession().setAttribute(USER_ATTRIBUTE, user);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Authentication error", e);
            response.sendRedirect(ERROR_PAGE);
        }
    }
}
