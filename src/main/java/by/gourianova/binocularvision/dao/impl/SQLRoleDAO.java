package by.gourianova.binocularvision.dao.impl;

import by.gourianova.binocularvision.bean.Role;
import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.RoleDAO;
import by.gourianova.binocularvision.util.ConfigurationManager;


import java.sql.*;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.apache.logging.log4j.core.util.Closer.close;

public class SQLRoleDAO implements RoleDAO {

    private final static String SQL_CREATE_TABLE_ROLES = "create table if not exists users(id INT(11) NOT NULL auto_increment," +
            "Role varchar(20),  primary key (id) );";
    private final static String SQL_CREATE_ROLE = "INSERT INTO roles (Role) VALUES (?);";

    private final static String SQL_FIND_ALL_ROLES = "SELECT * FROM roles";

    private final static String SQL_FIND_ROLE_BY_ID = "SELECT * FROM roles WHERE id = ?;";

    private static Connection connection;

    //TODO: move code to connectionPool code: to create!
    public static void connectionToData() {

        String db_url = ConfigurationManager.getProperty("dburl");
        String db_user = ConfigurationManager.getProperty("dbuser");
        String db_password = ConfigurationManager.getProperty("dbpassword");
        try {
            connection = DriverManager.getConnection(db_url, db_user, db_password);
        } catch (SQLException e) {
            log.println("Couldn'n connect to data base");
            e.printStackTrace();
        }
    }

    @Override
    public boolean createRole(Role entity) throws DAOException {

        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean isCreated=false;
        try {connectionToData();
            statement = connection.createStatement();

            //TODO: delete or remove?
            statement.executeUpdate(SQL_CREATE_TABLE_ROLES);
            preparedStatement = connection.prepareStatement(SQL_CREATE_ROLE);
            preparedStatement.setString(1, entity.getRole());
            preparedStatement.executeUpdate();
            isCreated = true;
        } catch (SQLException e) {
            throw new DAOException("Error in createEntity", e);
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
        return isCreated;
    }

    @Override
    public ArrayList<Role> findAll() throws DAOException {
        ArrayList<Role> rolesList = new ArrayList<>();



        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try { connectionToData();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ROLES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt(1));
                role.setRole(resultSet.getString(2));
                rolesList.add(role);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAll", e);
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
    public Role findEntityById(Integer id) throws DAOException {
        String db_url = ConfigurationManager.getProperty("dburl");
        String db_user = ConfigurationManager.getProperty("dbuser");
        String db_password = ConfigurationManager.getProperty("dbpassword");
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Role role = null;
        try {
            connection = DriverManager.getConnection(db_url, db_user, db_password);
            preparedStatement = connection.prepareStatement(SQL_FIND_ROLE_BY_ID);
             resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = new Role();
                role.setId(resultSet.getInt(1));
                role.setRole(resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findEntityById", e);
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


}
