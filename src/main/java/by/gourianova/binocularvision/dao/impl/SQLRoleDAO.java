package by.gourianova.binocularvision.dao.impl;

import by.gourianova.binocularvision.bean.Role;
import by.gourianova.binocularvision.dao.DAOException2;
import by.gourianova.binocularvision.dao.RoleDAO;
import by.gourianova.binocularvision.db.ConnectionPool;
import by.gourianova.binocularvision.db.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.apache.logging.log4j.core.util.Closer.close;

public class SQLRoleDAO implements RoleDAO {


    private final static String SQL_CREATE_TABLE_ROLES = "create table if not exists users(id INT(11) NOT NULL auto_increment," +
            "Role varchar(20),  primary key (id) );";

    private final static String SQL_FIND_ALL_ROLES = "SELECT * FROM roles";
    private final static String SQL_CREATE_ROLE = "INSERT INTO roles (Role) VALUES (?);";
    private final static String SQL_FIND_ROLE_BY_ID = "SELECT * FROM roles WHERE id = ?;";

    @Override
    public ArrayList<Role> findAll() throws DAOException2 {
        ArrayList<Role> rolesList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ROLES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt(1));
                role.setRole(resultSet.getString(2));
                rolesList.add(role);
            }
        } catch (SQLException e) {
            throw new DAOException2("Error in findAll", e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rolesList;
    }

    @Override
    public Role findEntityById(Integer id) throws DAOException2 {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        Role role = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ROLE_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = new Role();
                role.setId(resultSet.getInt(1));
                role.setRole(resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new DAOException2("Error in findEntityById", e);
        } finally {
            //TODO: decide *.close() or close(*)
            try {close(preparedStatement);
            } catch (Exception e) {
                            log.println("couldn't close preparedStatement SQLRoleDAO.findEntityById");
                e.printStackTrace();
            }
            try {
                close(connection);
            } catch (Exception e) {
                log.println("couldn't close connection at SQLRoleDAO.findEntityById");
                e.printStackTrace();
            }
        }
        return role;
    }

    @Override
    public boolean createEntity(Role entity) throws DAOException2 {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreate;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_ROLE);
            preparedStatement.setString(1, entity.getRole());
            preparedStatement.executeUpdate();
            isCreate = true;
        } catch (SQLException e) {
            throw new DAOException2("Error in createEntity", e);
        } finally {
            try {
                close(preparedStatement);
            } catch (Exception e) {
                log.println("couldn't close preparedStatement SQLRoleDAO.findEntityById");
                e.printStackTrace();
            }
            try {
                close(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isCreate;
    }
}
