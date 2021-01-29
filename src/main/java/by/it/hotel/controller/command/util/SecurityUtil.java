package by.it.hotel.controller.command.util;

import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import by.it.hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SecurityUtil {
    private static final Logger logger = LogManager.getLogger(SecurityUtil.class);

    public static boolean isSecurePage(String path) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        List<String> permissions = null;

        try {
            permissions = userService.retrievePermissions();
        } catch (ServiceException e) {
            logger.error(e);
        }
        return permissions != null && permissions.contains(path);
    }

    public static boolean hasPermission(int roleId, String path) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        List<String> permissions = null;

        try {
            permissions = userService.retrievePermissions(roleId);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return permissions != null && permissions.contains(path);
    }
}
