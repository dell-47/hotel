package by.it.hotel.dao.impl;

import by.it.hotel.dao.DaoException;
import by.it.hotel.dao.UserDao;
import by.it.hotel.dao.pool.ConnectionPool;
import by.it.hotel.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String CREATE_USER_QUERY = "INSERT INTO users (login, password, first_name, last_name, email, role) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
    private static final String RETRIEVE_USER_QUERY = "SELECT * FROM users WHERE login = ?";
    private static final String RETRIEVE_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String RETRIEVE_ROLE_PERMISSIONS_QUERY = "SELECT pattern FROM roles r LEFT JOIN permissions p ON r.id = p.role WHERE r.id = ?";
    private static final String RETRIEVE_ALL_PERMISSIONS_QUERY = "SELECT pattern FROM permissions";

    @Override
    public void createUser(User user) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(CREATE_USER_QUERY);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setInt(6, user.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }

    @Override
    public void updateUser(User user) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(UPDATE_USER_QUERY);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }

    @Override
    public User retrieveUser(String login) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        User user = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(RETRIEVE_USER_QUERY);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getInt("role"));
                user.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return user;
    }

    @Override
    public List<String> retrievePermissions(int roleId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> userPermissions = new ArrayList<>();
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(RETRIEVE_ROLE_PERMISSIONS_QUERY);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                userPermissions.add(rs.getString("pattern"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return userPermissions;
    }

    @Override
    public List<String> retrievePermissions() throws DaoException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<String> permissions = new ArrayList<>();
        try {
            con = pool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(RETRIEVE_ALL_PERMISSIONS_QUERY);
            while (rs.next()) {
                permissions.add(rs.getString("pattern"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, st, rs);
        }
        return permissions;
    }
}
