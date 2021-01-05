package by.it.hotel.service.impl;

import by.it.hotel.dao.DaoException;
import by.it.hotel.dao.DaoProvider;
import by.it.hotel.dao.UserDao;
import by.it.hotel.entity.User;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final DaoProvider daoProvider = DaoProvider.getInstance();
    private final UserDao userDao = daoProvider.getUserDao();

    @Override
    public User retrieveUser(String login, String password) throws ServiceException {
        User user = null;
        try {
            user = userDao.retrieveUser(login, password);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean createUser(User user) throws ServiceException {
        boolean registration = false;
        try {
            registration = userDao.createUser(user);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return registration;
    }

    @Override
    public List<String> retrieveRolePermissions(int roleId) throws ServiceException {
        List<String> userPermissions;
        try {
            userPermissions = userDao.retrieveRolePermissions(roleId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return userPermissions;
    }
}