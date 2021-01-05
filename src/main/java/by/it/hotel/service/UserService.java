package by.it.hotel.service;

import by.it.hotel.dao.DaoException;
import by.it.hotel.entity.User;

import java.util.List;

public interface UserService {
    User authorization(String login, String password) throws ServiceException;
    boolean registration(User user) throws ServiceException;
    List<String> rolePermissions(int roleId) throws ServiceException;
}
