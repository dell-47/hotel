package by.it.hotel.dao;

import by.it.hotel.entity.User;

import java.util.List;

/**
 * This is an interface for user dao layer.
 */
public interface UserDao {

    /**
     * This method is used to retrieve user by login.
     * @param login User's login.
     * @return Returns the required user.
     * @throws DaoException Throws if database operation fails.
     */
    User retrieveUser(String login) throws DaoException;


    /**
     * This method is used to create a user.
     * @param user This entity is used when creating a user.
     * @throws DaoException Throws if database operation fails.
     */
    void createUser(User user) throws DaoException;


    /**
     * This method is used to update user information.
     * @param user This entity is used when updating user information.
     * @throws DaoException Throws if database operation fails.
     */
    void updateUser(User user) throws DaoException;


    /**
     * This method is used to retrieve permissions.
     * @return Returns a list of permissions.
     * @throws DaoException Throws if database operation fails.
     */
    List<String> retrievePermissions() throws DaoException;


    /**
     * This method is used to retrieve permissions for the required user's role.
     * @param roleId User's role id.
     * @return Returns a list of permissions for the required user's role.
     * @throws DaoException Throws if database operation fails.
     */
    List<String> retrievePermissions(int roleId) throws DaoException;
}