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

    private final static String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?;";

//TODO: fix and add Create_time

//	private final static String SQL_CREATE_USER = "INSERT INTO users (Login, Password, First_Name, Last_Name,  Balance, Create_time) VALUES (?, ?, ?, ?, ?,?);";


    static {
        MYSQLDriverLoader.getInstance();
    }


    @Override
    public boolean registration(RegistrationInfo regInfo) throws DAOException {

        //TODO: через прокси
        //Proxy
        String db_url = ConfigurationManager.getProperty("dburl");
        String db_user = ConfigurationManager.getProperty("dbuser");
        String db_password = ConfigurationManager.getProperty("dbpassword");
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isRegistered = false;
		ArrayList<User> usersList = new ArrayList<>();
		try {
			usersList=findAll();
			for (User user:usersList){
				log.println(user.getLogin()+" "+user.getLastName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//TODO: проверять на уникальность логина
        //сначала проверим, нет ли такого логина в базе
        try {
            connection = DriverManager.getConnection(db_url, db_user, db_password);

            statement = connection.createStatement();

            //TODO: delete?
            statement.executeUpdate(SQL_CREATE_TABLE_USERS);

            String login = regInfo.getEmail();
            preparedStatement = connection.prepareStatement(SQL_CREATE_USER);
            System.out.println(regInfo.getEmail());
            preparedStatement.setString(1, regInfo.getEmail());
            preparedStatement.setString(2, regInfo.getPassword());
            preparedStatement.setString(3, regInfo.getName());
            preparedStatement.setString(4, regInfo.getSurname());
            preparedStatement.setBigDecimal(5, new BigDecimal(regInfo.getBalance()));
            //Object date = regInfo.getDateTime(); //LocalDateTime.now();
            //preparedStatement.setLong(6, new java.sql.Timestamp((Long) date).getTime());


            if (preparedStatement == null) log.println("SQLUserDAO.registration preparedStatement==null");

            preparedStatement.executeUpdate();

			 usersList = new ArrayList<>();
			try {
				usersList=findAll();
				for (User user:usersList){
					log.println(user.getLogin()+" "+user.getLastName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
    public ArrayList<User> findAll() throws DAOException {
        ArrayList<User> usersList = new ArrayList<>();
		String db_url = ConfigurationManager.getProperty("dburl");
		String db_user = ConfigurationManager.getProperty("dbuser");
		String db_password = ConfigurationManager.getProperty("dbpassword");
        //Proxy
        Connection connection = null;
        Statement statement = null;
		ResultSet resultSet=null;
        try {
            connection = DriverManager.getConnection(db_url, db_user, db_password);

             resultSet =statement.executeQuery(SQL_FIND_ALL_USER);

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String login = resultSet.getString("Login");
                String password = resultSet.getString("Password");
                String name = resultSet.getString("First_Name");
                String surname = resultSet.getString("Last_Name");
                BigDecimal balance = resultSet.getBigDecimal("Balance");
                int role = resultSet.getInt("Role");
                LocalDate create_date = resultSet.getDate("Create_time").toLocalDate();
                usersList.add(new User(id, login, password, name, surname, balance, role, create_date));
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

        return usersList;
    }


    @Override
    public User authorization(String login, String password) {


        //TODO: через прокси?
        //Proxy
        String db_url = ConfigurationManager.getProperty("dburl");
        String db_user = ConfigurationManager.getProperty("dbuser");
        String db_password = ConfigurationManager.getProperty("dbpassword");
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;


//TODO: were MD5
        //	password=new MD5().md5Encode(password);
        try {
            connection = DriverManager.getConnection(db_url, db_user, db_password);

            statement = connection.createStatement();


            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("First_Name");
                String surname = resultSet.getString("Last_Name");
                BigDecimal balance = resultSet.getBigDecimal("Balance");
                //TODO: role!!!
                int role = 3;//resultSet.getInt("Role");
                LocalDate create_date = resultSet.getDate("Create_time").toLocalDate();
                user = new User(id, login, password, name, surname, balance, role, create_date);
                log.println("found user " + name + " " + surname + " " + create_date);
            }
        } catch (SQLException e) {
            if (user == null)
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
		if (user!=null) log.println("AUTHORISATION OK: SQLUserDAO");



		return user;
    }
}

