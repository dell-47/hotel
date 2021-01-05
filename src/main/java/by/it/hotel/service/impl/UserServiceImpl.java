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
    public User authorization(String login, String password) throws ServiceException {
        User user = null;
        try {
            user = userDao.authorization(login, password);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean registration(User user) throws ServiceException {
        boolean registration = false;
        try {
            registration = userDao.registration(user);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return registration;
    }

    @Override
    public List<String> rolePermissions(int roleId) throws ServiceException {
        List<String> userPermissions;
        try {
            userPermissions = userDao.rolePermissions(roleId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return userPermissions;
    }
}
