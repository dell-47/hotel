package by.it.hotel.service;

import by.it.hotel.entity.User;

import java.util.List;

public interface UserService {
    User retrieveUser(String login) throws ServiceException;
    void createUser(User user) throws ServiceException;
    void updateUser(User user) throws ServiceException;
    List<String> retrievePermissions() throws ServiceException;
    List<String> retrievePermissions(int roleId) throws ServiceException;
}
