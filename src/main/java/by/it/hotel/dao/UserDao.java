package by.it.hotel.dao;

import by.it.hotel.entity.User;

import java.util.List;

public interface UserDao {

    User authorization(String login, String password) throws DaoException;
    boolean registration(User user) throws DaoException;
    List<String> rolePermissions(int roleId) throws DaoException;

}
