package by.gourianova.binocularvision.dao.impl;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.dao.UserDAO;
import by.gourianova.binocularvision.db.ConnectionPool;
import by.gourianova.binocularvision.db.ProxyConnection;
import by.gourianova.binocularvision.util.ConfigurationManager;
import by.gourianova.binocularvision.util.MD5;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.apache.logging.log4j.core.util.Closer.close;

//import by.gourianova.binocularvision.dao.DAOException;

public class SQLUserDAO implements UserDAO {

	private final static String SQL_CREATE_TABLE_USER="create table if not exists users(id INT(11) NOT NULL auto_increment," +
			"Login varchar(10) not null unique , Password varchar(32) not null,First_Name varchar(15) not null, " +
			"Last_Name varchar(15) not null, Balance DECIMAL (6,2), Create_time DATETIME,  primary key (id) );";
	private final static String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?;";
//	private final static String SQL_CREATE_USER = "INSERT INTO users (Login, Password, First_Name, Last_Name) VALUES (?, ?, ?, ?);";
	private final static String SQL_CREATE_USER = "INSERT INTO users (Login, Password, First_Name, Last_Name,  Balance, Create_time) VALUES (?, ?, ?, ?, ?,?);";
	private final static String SQL_FIND_ALL_USER = "SELECT * FROM users;";
	private final static String SQL_FIND_USER_BY_LOGIN_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?;";

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
		Statement statement=null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		boolean isRegistered = false;

		//TODO: проверять на уникальность логина
		//сначала проверим, нет ли такого логина в базе
		try {
			connection=	DriverManager.getConnection(db_url,db_user, db_password );
			//connection=	DriverManager.getConnection("jdbc:mysql://localhost:3306/apptrainer",db_user, db_password );

			statement=connection.createStatement();

			//TODO: delete?
			statement.executeUpdate(SQL_CREATE_TABLE_USER);

  			String login = regInfo.getEmail();
			preparedStatement = connection.prepareStatement(SQL_CREATE_USER);
			System.out.println(regInfo.getEmail());
			preparedStatement.setString(1,regInfo.getEmail());
			preparedStatement.setString(2, regInfo.getPassword());
			preparedStatement.setString(3, regInfo.getName());
			preparedStatement.setString(4, regInfo.getSurname());
			String balance = "3.3";
			preparedStatement.setBigDecimal(5, new BigDecimal(balance));
			Object date = LocalDateTime.now();
			preparedStatement.setLong(6, new java.sql.Timestamp((Long) date).getTime());
			if (preparedStatement==null) System.out.println("preparedStatement==null");
			preparedStatement.executeUpdate();
			isRegistered = true;
		} catch (SQLException e) {
			if(isRegistered == false)
			throw new DAOException("Error in registration method", e);
		} finally {
			if (resultSet!=null) {
				try {
					resultSet.close();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
			try {
				statement.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (SQLException throwables) {
				System.out.println("Couldn't close preparedStatement");
				throwables.printStackTrace();
			}

			if (connection != null) {
				try {
					close(connection);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return isRegistered;
	}




	@Override
	public Collection<User> findAll() throws Exception {
		ArrayList<User> usersList = new ArrayList<>();
		//Proxy
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1/apptrainer?useSSL=false&serverTimezone=UTC",
					"root", "778899");
			connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USER);
			ResultSet resultSet = preparedStatement.executeQuery();
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
			throw new Exception("Error in findAll method", e);
		} finally {
			close(preparedStatement);
			if (connection != null)  close(connection);
		}
		return usersList;
	}


	@Override
	public User authorization(String login, String password) throws DAOException {
		ProxyConnection connection = null;
		PreparedStatement preparedStatement = null;
		User user=null;

		password=new MD5().md5Encode(password);
		try {
				connection = ConnectionPool.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_PASSWORD);
				preparedStatement.setString(1, login);
				preparedStatement.setString(2,password);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet!=null){
					//resultSet.last();
					//if (resultSet.getRow() == 1) {
						int id = resultSet.getInt("Id");
						String name = resultSet.getString("First_Name");
						String surname = resultSet.getString("Last_Name");
						BigDecimal balance = resultSet.getBigDecimal("Balance");
						int role = resultSet.getInt("Role");
						LocalDate create_date = resultSet.getDate("Create_time").toLocalDate();
						 user= new User(id, login, password, name, surname, balance, role, create_date);
				}


		}  //     log.println("USER AUTHORIZATION");
		catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	//TODO: revise close connections!!!!
		} finally {
		close(preparedStatement);
		if (connection != null)  close(connection);
	}
		System.out.println("USER AUTHORIZATION");
		return user;
	}
}


