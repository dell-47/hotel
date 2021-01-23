package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;

import by.it.hotel.controller.command.utils.UserUtil;
import by.it.hotel.entity.User;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import by.it.hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.it.hotel.controller.command.impl.CommandConstants.*;

public class SaveProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SaveProfileCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        User beforeEditUser = (User) request.getSession().getAttribute("user");
        User afterEditUser = new User();
        afterEditUser.setFirstName(request.getParameter(FIRST_NAME));
        afterEditUser.setLastName(request.getParameter(LAST_NAME));
        afterEditUser.setEmail(request.getParameter(EMAIL));

        User updatedUser = UserUtil.getUpdatedUser(beforeEditUser, afterEditUser);

        try {
            userService.updateUser(updatedUser);
        } catch (ServiceException e) {
            logger.error(e);
            response.sendRedirect(ERROR_PAGE);
        }

        request.getSession().setAttribute("user", updatedUser);
        response.sendRedirect(GO_TO_PROFILE_PAGE);
    }
}
