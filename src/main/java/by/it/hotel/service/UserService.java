package by.it.hotel.service;

import by.it.hotel.entity.User;

import java.util.List;

public interface UserService {
    User retrieveUser(String login, String password) throws ServiceException;
    boolean createUser(User user) throws ServiceException;
    List<String> retrieveRolePermissions(int roleId) throws ServiceException;
}
