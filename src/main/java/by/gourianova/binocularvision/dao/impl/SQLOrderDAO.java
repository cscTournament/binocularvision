package by.gourianova.binocularvision.dao.impl;

import by.gourianova.binocularvision.bean.App;
import by.gourianova.binocularvision.bean.Order;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.OrderDAO;
import by.gourianova.binocularvision.dao.db.ConfigurationManager;
import by.gourianova.binocularvision.dao.db.ConnectionPool;
import by.gourianova.binocularvision.dao.db.ProxyConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.apache.logging.log4j.core.util.Closer.close;

public class SQLOrderDAO implements OrderDAO {

    private final static String SQL_CREATE_TABLE_ORDERS = "create table if not exists users(id INT(11) NOT NULL auto_increment," +
            "Start_Date DATETIME, End_Date DATETIME,  Value DECIMAL (4,2),Users_id INT(11),Apps_id INT(11), primary key (id) ) DEFAULT CHARSET=utf8;";

    private final static String SQL_CREATE_ORDER= "INSERT INTO orders (Start_Date, Value, Users_id, Apps_id) VALUES (?, ?, ?, ?);";
    private final static String SQL_FIND_ORDER = "SELECT * FROM orders WHERE Users_Id=? ORDER BY id DESC LIMIT 1;";
    private final static String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders;";
    private final static String SQL_FIND_UNCLOSED_ORDERS = "SELECT * FROM orders WHERE End_Date IS NULL;";
    private final static String SQL_FIND_UNCLOSED_USER_ORDER = "SELECT * FROM orders WHERE Users_Id=? AND End_Date IS NULL;";
    private final static String SQL_FIND_ALL_USER_ORDERS = "SELECT * FROM orders WHERE Users_Id=?;";
    private final static String SQL_CLOSE_ORDER = "UPDATE orders SET End_Date=now(), Value=? WHERE Users_Id=? AND End_Date IS NULL;";
    private final static String SQL_UPDATE_USER = "UPDATE users SET Balance=?, Roles_Id=2 WHERE Id=?;";
    private final static String SQL_UPDATE_APP = "UPDATE apps SET In_Rent=0 WHERE Id=?;";
    private static Connection connection;

    public static void connectionToData() {
//TODO: test
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
    public boolean createEntity(Order order) throws DAOException {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isCreated = false;

        //TODO: is it necessary to check uniq of login  or rewrite patern: (login email is uniq!)?

        try {
            connectionToData();
            statement = connection.createStatement();

            //TODO: delete or remove?
            statement.executeUpdate(SQL_CREATE_TABLE_ORDERS);
            //  statement.executeUpdate(SQL_SET_UTF8);

            preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER);
            Object startRent= order.getStartRent();
            preparedStatement.setLong(1,new java.sql.Timestamp((Long)startRent).getTime());
            preparedStatement.setBigDecimal(2,order.getValue());
            preparedStatement.setInt(3, order.getUserId());
            preparedStatement.setInt(4, order.getAppId());


            if (preparedStatement == null) log.println("SQLOrderDAO.createEntity preparedStatement==null");

            preparedStatement.executeUpdate();

            isCreated = true;


        } catch (SQLException e) {
            if (isCreated== false)
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
        return isCreated;
    }

    @Override
    public ArrayList<Order> findAll() throws DAOException {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt(1));
                order.setStartRent(resultSet.getTimestamp(2).toLocalDateTime());
                if (!(resultSet.getTimestamp(3) == null)) {
                    order.setEndRent(resultSet.getTimestamp(3).toLocalDateTime());
                }
                order.setValue(resultSet.getBigDecimal(4));
                order.setUserId(resultSet.getInt(5));
                order.setAppId(resultSet.getInt(6));

                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAll method", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    log.println("Couldn't close !null preparedStatement");
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    close(connection);
                } catch (Exception e) {
                    log.println("Couldn't close !null connection");
                    e.printStackTrace();
                }
            }

        }
        return ordersList;
    }

    @Override
    public Order findEntityById(Integer userId) throws DAOException {
        Order order = new Order();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ORDER);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt(1));
                order.setStartRent(resultSet.getTimestamp(2).toLocalDateTime());
                if (!(resultSet.getTimestamp(3) == null)) {
                    order.setEndRent(resultSet.getTimestamp(3).toLocalDateTime());
                }
                order.setValue(resultSet.getBigDecimal(4));
                order.setUserId(resultSet.getInt(5));
                order.setAppId(resultSet.getInt(6));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findEntityById method", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    log.println("Couldn't close !null preparedStatement");
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    close(connection);
                } catch (Exception e) {
                    log.println("Couldn't close !null connection");
                    e.printStackTrace();
                }
            }

        }
        return order;
    }





    @Override
    public Order findUnclosedOrder(Integer userId) throws DAOException {
        Order order = new Order();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_UNCLOSED_USER_ORDER);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order.setId(resultSet.getInt(1));
                order.setStartRent(resultSet.getTimestamp(2).toLocalDateTime());
                order.setUserId(resultSet.getInt(5));
                order.setAppId(resultSet.getInt(6));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findUnclosedOrder method", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    log.println("Couldn't close !null preparedStatement");
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    close(connection);
                } catch (Exception e) {
                    log.println("Couldn't close !null connection");
                    e.printStackTrace();
                }
            }
        }
        return order;
    }

    public ArrayList<Order> findAllUserOrders(Integer userId) throws DAOException {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USER_ORDERS);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt(1));
                order.setStartRent(resultSet.getTimestamp(2).toLocalDateTime());
                if (!(resultSet.getTimestamp(3) == null)) {
                    order.setEndRent(resultSet.getTimestamp(3).toLocalDateTime());
                }
                order.setValue(resultSet.getBigDecimal(4));
                order.setUserId(resultSet.getInt(5));
                order.setAppId(resultSet.getInt(6));

                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAllUserOrders method", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    log.println("Couldn't close !null preparedStatement");
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    close(connection);
                } catch (Exception e) {
                    log.println("Couldn't close !null connection");
                    e.printStackTrace();
                }
            }

        }
        return ordersList;
    }

    @Override
    public ArrayList<Order> findUnclosed() throws DAOException {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_UNCLOSED_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt(1));
                order.setStartRent(resultSet.getTimestamp(2).toLocalDateTime());
                if (!(resultSet.getTimestamp(3) == null)) {
                    order.setEndRent(resultSet.getTimestamp(3).toLocalDateTime());
                }
                order.setValue(resultSet.getBigDecimal(4));
                order.setUserId(resultSet.getInt(5));
                order.setAppId(resultSet.getInt(6));
                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findUnclosed method", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    log.println("Couldn't close !null preparedStatement");
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    close(connection);
                } catch (Exception e) {
                    log.println("Couldn't close !null connection");
                    e.printStackTrace();
                }
            }

        }
        return ordersList;
    }

    @Override
    public void closeOrder(User user, BigDecimal value, App app) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_CLOSE_ORDER);
            preparedStatement.setBigDecimal(1, value);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setBigDecimal(1, user.getBalance().subtract(value));
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_APP);
            preparedStatement.setInt(1, app.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Error in closeOrder method", e);
            }
            throw new DAOException("Error in closeOrder method", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    log.println("Couldn't close !null preparedStatement");
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    close(connection);
                } catch (Exception e) {
                    log.println("Couldn't close !null connection");
                    e.printStackTrace();
                }
            }
        }


    }
}


