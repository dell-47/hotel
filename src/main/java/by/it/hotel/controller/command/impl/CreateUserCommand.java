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


public class CreateUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        User user = new User();
        String username = request.getParameter(USERNAME);
        user.setFirstName(request.getParameter(FIRST_NAME));
        user.setLastName(request.getParameter(LAST_NAME));
        user.setEmail(request.getParameter(EMAIL));
        user.setUsername(username);
        user.setPassword(BCrypt.hashpw(request.getParameter(PASSWORD), BCrypt.gensalt()));
        user.setRole(ROLE_USER);

        try {
            if (userService.retrieveUser(username) == null) {
                userService.createUser(user);
                request.getSession().setAttribute("action", ACTION_SIGN_UP);
                response.sendRedirect(SUCCESSFUL_ACTION_PAGE);
            } else {
                request.setAttribute("usernameError", USERNAME_ERROR_MESSAGE);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_SIGN_UP_PAGE);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            logger.error("User creation error", e);
            response.sendRedirect(ERROR_PAGE);
        }
    }
}
