package by.gourianova.binocularvision.dao;

import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.db.ConnectionPool;
import by.gourianova.binocularvision.db.ProxyConnection;
import by.gourianova.binocularvision.exception.DaoException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao extends AbstractDao<User> {
    private final static String SQL_CREATE_USER = "INSERT INTO users (First_Name, Last_Name, Login, Password, Balance) VALUES (?, ?, ?, ?, ?);";
    private final static String SQL_FIND_ALL_USER = "SELECT * FROM users;";
    private final static String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    private final static String SQL_FIND_USER_BY_LOGIN_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?;";
    private final static String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?;";
    private final static String SQL_UPDATE_BALANCE = "UPDATE users SET Balance=? WHERE Id=?;";
    private final static String SQL_UPDATE_ROLE = "UPDATE users SET Roles_Id=? WHERE Id=?;";

    @Override
    public ArrayList<User> findAll() throws DaoException {
        ArrayList<User> usersList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersList.add(buildUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return usersList;
    }

    @Override
    public User findEntityById(Integer id) throws DaoException {
        User user = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    @Override
    public boolean createEntity(User user) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreate;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getBalance().toString());
            preparedStatement.executeUpdate();
            isCreate = true;
        } catch (SQLException e) {
            throw new DaoException("Error in createEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isCreate;
    }

    public User findEntityByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityByLoginAndPassword method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    public boolean findEntityByLogin(String login) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityByLogin method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public User updateBalance(User user, BigDecimal newBalance) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE);
            preparedStatement.setBigDecimal(1, user.getBalance().add(newBalance));
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            user.setBalance(user.getBalance().add(newBalance));
        } catch (SQLException e) {
            throw new DaoException("Error in updateBalance method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    public void updateEntity(User user) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ROLE);
            preparedStatement.setInt(1, user.getRoleId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in updateEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setFirstName(resultSet.getString(2));
        user.setLastName(resultSet.getString(3));
        user.setLogin(resultSet.getString(4));
        user.setPassword(resultSet.getString(5));
        user.setBalance(resultSet.getBigDecimal(6));
        user.setRoleId(resultSet.getInt(7));
        return user;
    }
}
