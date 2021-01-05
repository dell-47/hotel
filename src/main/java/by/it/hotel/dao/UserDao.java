package by.it.hotel.dao;

import by.it.hotel.entity.User;

import java.util.List;

public interface UserDao {

    User retrieveUser(String login, String password) throws DaoException;
    boolean createUser(User user) throws DaoException;
    List<String> retrieveRolePermissions(int roleId) throws DaoException;

}
