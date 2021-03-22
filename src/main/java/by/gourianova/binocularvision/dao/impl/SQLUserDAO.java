package by.gourianova.binocularvision.dao.impl;


import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.UserDAO;
import by.gourianova.binocularvision.util.ConfigurationManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.apache.logging.log4j.core.util.Closer.close;


public class SQLUserDAO implements UserDAO {

    private final static String SQL_CREATE_TABLE_USERS = "create table if not exists users(id INT(11) NOT NULL auto_increment," +
            "Login varchar(10) not null unique , Password varchar(32) not null,First_Name varchar(15) not null, " +
            "Last_Name varchar(15) not null, Balance DECIMAL (6,2), Roles_id INT(11), Create_time DATETIME,  primary key (id) );";

    private final static String SQL_CREATE_USER = "INSERT INTO users (Login, Password, First_Name, Last_Name, Balance) VALUES (?, ?, ?, ?,?);";

    private final static String SQL_FIND_ALL_USERS = "SELECT * FROM users;";
    private final static String SQL_FIND_USER_BY_LOGIN_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?;";
    private final static String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    private final static String SQL_UPDATE_ROLE = "UPDATE users SET Roles_Id=? WHERE Id=?;";

    private static Connection connection;

    private static int id;
    private static String login;
    private static String password;
    private static String name;
    private static String surname;
    private static BigDecimal balance;
    //TODO: rewrite
    private static final int role = 2;
    //  private static LocalDate date;
    private static int roles_id;


    //   private final static String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?;";
    // private static final String OPEN_SESSION = "UPDATE users set session = 'online' WHERE id = ?;";

    //TODO: fix and add Create_time


    static {
        MYSQLDriverLoader.getInstance();
    }

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
    public boolean registration(RegistrationInfo regInfo) throws DAOException {

        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isRegistered = false;

        //TODO: is it necessary to check uniq of login  ?

        try {
            connectionToData();
            statement = connection.createStatement();

            //TODO: delete or remove?
            statement.executeUpdate(SQL_CREATE_TABLE_USERS);

            String login = regInfo.getLogin();
            preparedStatement = connection.prepareStatement(SQL_CREATE_USER);
            System.out.println(regInfo.getLogin());
            preparedStatement.setString(1, regInfo.getLogin());
            preparedStatement.setString(2, regInfo.getPassword());
            preparedStatement.setString(3, regInfo.getName());
            preparedStatement.setString(4, regInfo.getSurname());
            preparedStatement.setBigDecimal(5, new BigDecimal(regInfo.getBalance()));
            //Object date = regInfo.getDateTime(); //LocalDateTime.now();
            //preparedStatement.setLong(6, new java.sql.Timestamp((Long) date).getTime());


            if (preparedStatement == null) log.println("SQLUserDAO.registration preparedStatement==null");

            preparedStatement.executeUpdate();

            isRegistered = true;


        } catch (SQLException e) {
            if (isRegistered == false)
                //TODO:&&&&
                throw new DAOException("Error in registration method", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.println("Couldn't close resultSet");
                    e.printStackTrace();
                }
            }
            try {
                statement.close();
            } catch (SQLException e) {
                log.println("Couldn't close statement");
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.println("Couldn't close preparedStatement");
                e.printStackTrace();
            }

            if (connection != null) {
                try {
                    close(connection);
                } catch (Exception e) {
                    log.println("Couldn't close not null connection");
                    e.printStackTrace();
                }
            }
        }
        return isRegistered;
    }


    @Override
    public User authorization(String login, String password) {


        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;


        try {
            connectionToData();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            int count=0;
            //TODO: while?
            while (resultSet.next()) {
                id = resultSet.getInt("Id");
                name = resultSet.getString("First_Name");
                surname = resultSet.getString("Last_Name");
                balance = resultSet.getBigDecimal("Balance");
                //TODO: role and date!!!
                //  int role = resultSet.getInt("Role"); int role = 2;
                int role = 2;
                // if (surname=="Gourianova")  role = 3;

                // LocalDate create_date = resultSet.getDate("Create_time").toLocalDate();
                user = new User(id, login, password, name, surname, balance, role);//, create_date);
                log.println(count+" found user " + name + " " + surname);

            }
            log.println("found " + count+ " users");

        } catch (SQLException e) {

            //TODO:&&&&
            try {
                throw new DAOException("Error in SQLUserDAO.authorization method", e);
            } catch (DAOException daoException) {
                daoException.printStackTrace();
            }
        } finally {

            try {
                resultSet.close();
            } catch (SQLException e) {
                log.println("Couldn't close resultSet");
                e.printStackTrace();
            }
        }

        try {
            preparedStatement.close();
        } catch (SQLException e) {
            log.println("Couldn't close preparedStatement");
            e.printStackTrace();
        }

        if (connection != null) {
            try {
                close(connection);
            } catch (Exception e) {
                log.println("Couldn't close not null connection");
                e.printStackTrace();
            }
        }
        if (user != null) log.println("AUTHORISATION OK: SQLUserDAO");


        return user;
    }

    //TODO:debug
    @Override
    public ArrayList<User> findAll() throws DAOException {
        ArrayList<User> usersList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connectionToData();
            log.println("connection Ok");
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("Id");
             //   log.println(id + "id");
                login = resultSet.getString("Login");
                password = resultSet.getString("Password");
                name = resultSet.getString("First_Name");
                surname = resultSet.getString("Last_Name");
                balance = resultSet.getBigDecimal("Balance");
                int role = 2;//resultSet.getInt("Role");
                // LocalDate create_date = resultSet.getDate("Create_time").toLocalDate();
                //log.println(id + login + password + name + surname + balance + role);
                usersList.add(new User(id, login, password, name, surname, balance, role));//, create_date));
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findAll method", e);
        } finally {
            try {

                resultSet.close();
            } catch (SQLException e) {
                log.println("Couldn't close resultSet");
                e.printStackTrace();
            }

            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.println("Couldn't close  preparedStatement");
                e.printStackTrace();
            }


            if (connection != null) {
                try {
                    close(connection);
                } catch (Exception e) {
                    log.println("Couldn't close not null connection");
                    e.printStackTrace();
                }
            }
        }

        return usersList;
    }

    @Override
    public User findUserById(Integer id) throws DAOException {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connectionToData();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //TODO: build?
                //user = buildUser(resultSet);
                //Integer id = resultSet.getInt("Id");
                String login = resultSet.getString("Login");
                String password = resultSet.getString("Password");
                String name = resultSet.getString("First_Name");
                String surname = resultSet.getString("Last_Name");
                BigDecimal balance = resultSet.getBigDecimal("Balance");
                int role = resultSet.getInt("Role");
                user = new User(id, login, password, name, surname, balance, role);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in SQLUserDAO.findUserById method", e);
        } finally {
            try {
                close(preparedStatement);
            } catch (Exception e) {
                log.println("Couldn't close preparedStatement");
                e.printStackTrace();
            }
            if (connection != null) {

                try {
                    close(connection);
                } catch (Exception e) {
                    log.println("Couldn't close not null connection");
                    e.printStackTrace();
                }
            }
        }
        return user;
    }
    @Override
    public void updateUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connectionToData();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ROLE);
            preparedStatement.setInt(1, user.getRoleId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in updateEntity method", e);
        } finally {
            try {
                close(preparedStatement);
            } catch (Exception e) {
                log.println("Couldn't close preparedStatement");
                e.printStackTrace();
            }
            try {
                close(connection);
            } catch (Exception e) {
                log.println("Couldn't close not null connection");
                e.printStackTrace();
            }
        }
    }

}



