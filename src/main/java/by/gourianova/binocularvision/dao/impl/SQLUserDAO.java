package by.gourianova.binocularvision.dao.impl;


import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.UserDAO;
import by.gourianova.binocularvision.util.ConfigurationManager;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.apache.logging.log4j.core.util.Closer.close;


public class SQLUserDAO implements UserDAO {

    private final static String SQL_CREATE_TABLE_USERS = "create table if not exists users(id INT(11) NOT NULL auto_increment," +
            "Login varchar(10) not null unique , Password varchar(32) not null,First_Name varchar(15) not null, " +
            "Last_Name varchar(15) not null, Balance DECIMAL (6,2), Roles_id INT(11), Create_time DATETIME,  primary key (id) );";

    private final static String SQL_CREATE_USER = "INSERT INTO users (Login, Password, First_Name, Last_Name, Balance) VALUES (?, ?, ?, ?,?);";

    private final static String SQL_FIND_ALL_USER = "SELECT * FROM users;";
    private final static String SQL_FIND_USER_BY_LOGIN_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?;";

    private static Connection connection;

    private static int id;
    private static String login;
    private static String password;
    private static String name;
    private static String surname;
    private static String balance;
    private static LocalDate date;
    private static int roles_id;
    private static String status;


 //   private final static String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?;";
   // private static final String OPEN_SESSION = "UPDATE users set session = 'online' WHERE id = ?;";

    //TODO: fix and add Create_time

//	private final static String SQL_CREATE_USER = "INSERT INTO users (Login, Password, First_Name, Last_Name,  Balance, Create_time) VALUES (?, ?, ?, ?, ?,?);";

    static {
      MYSQLDriverLoader.getInstance();
    }

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

            //TODO: delete?
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


        //TODO: через прокси?
        //Proxy
      //  Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;


    try{        connectionToData();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
           //TODO: while?
           if (resultSet.next()) {

               int id = resultSet.getInt("Id");
               String name = resultSet.getString("First_Name");
               String surname = resultSet.getString("Last_Name");
               BigDecimal balance = resultSet.getBigDecimal("Balance");
               //TODO: role and date!!!
               //  int role = resultSet.getInt("Role");
               int role = 3;
               // LocalDate create_date = resultSet.getDate("Create_time").toLocalDate();
               user = new User(id, login, password, name, surname, balance, role);//, create_date);
               log.println("found user " + name + " " + surname);

           }


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
		if (user!=null) log.println("AUTHORISATION OK: SQLUserDAO");



		return user;
    }
//TODO:debug
 @Override
    public ArrayList<User> findAll() throws DAOException {
     ArrayList<User> usersList = new ArrayList<>();
/*        Statement statement = null;
        ResultSet resultSet=null;
        try {
            connectionToData();
            resultSet =statement.executeQuery(SQL_FIND_ALL_USER);

           if (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String login = resultSet.getString("Login");
                String password = resultSet.getString("Password");
                String name = resultSet.getString("First_Name");
                String surname = resultSet.getString("Last_Name");
                BigDecimal balance = resultSet.getBigDecimal("Balance");
                int role = resultSet.getInt("Role");
               // LocalDate create_date = resultSet.getDate("Create_time").toLocalDate();
                usersList.add(new User(id, login, password, name, surname, balance, role));//, create_date));
            }

        } catch (SQLException e) {
            throw new DAOException("Error in findAll method", e);
        } finally {try {

            resultSet.close();
        } catch (SQLException e) {
            log.println("Couldn't close resultSet");
            e.printStackTrace();
        }

            try {
                statement.close();
            } catch (SQLException e) {
                log.println("Couldn't close statement");
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
*/
        return usersList;
    }

}

