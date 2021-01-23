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
    public User retrieveUser(String login) throws ServiceException {
        User user;
        try {
            user = userDao.retrieveUser(login);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void createUser(User user) throws ServiceException {
        try {
            userDao.createUser(user);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        try {
            userDao.updateUser(user);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<String> retrievePermissions(int roleId) throws ServiceException {
        List<String> userPermissions;
        try {
            userPermissions = userDao.retrievePermissions(roleId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return userPermissions;
    }

    @Override
    public List<String> retrievePermissions() throws ServiceException {
        List<String> userPermissions;
        try {
            userPermissions = userDao.retrievePermissions();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return userPermissions;
    }
}
