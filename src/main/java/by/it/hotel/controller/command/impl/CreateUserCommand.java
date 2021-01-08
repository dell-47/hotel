package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.entity.User;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import by.it.hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        String page = SUCCESSFUL_ACTION_PAGE;
        User user = new User();
        user.setFirstName(request.getParameter(FIRST_NAME));
        user.setLastName(request.getParameter(LAST_NAME));
        user.setEmail(request.getParameter(EMAIL));
        user.setLogin(request.getParameter(USERNAME));
        user.setPassword(request.getParameter(PASSWORD));
        user.setRole(ROLE_USER);

        try {
            userService.createUser(user);
        } catch (ServiceException e) {
            logger.error(e);
            page = ERROR_PAGE;
        }

        request.getSession().setAttribute("action", ACTION_SIGN_UP);
        response.sendRedirect(page);
    }
}
