package by.gourianova.binocularvision.dao.impl;


import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.dao.UserDAO2;
import by.gourianova.binocularvision.db.ConnectionPool;
import by.gourianova.binocularvision.db.ProxyConnection;
import by.gourianova.binocularvision.util.myMD5;



import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.apache.logging.log4j.core.util.Closer.close;

//import by.gourianova.binocularvision.dao.DAOException;

public class SQLUserDAO implements UserDAO2 {
	private final static String SQL_CREATE_USER = "INSERT INTO users (Login, Password, First_Name, Last_Name,  Balance, Create_time) VALUES (?, ?, ?, ?, ?,?);";
	private final static String SQL_FIND_ALL_USER = "SELECT * FROM users;";
	private final static String SQL_FIND_USER_BY_LOGIN_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?;";

	//private final static String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";

	//private final static String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?;";
	//private final static String SQL_UPDATE_BALANCE = "UPDATE users SET Balance=? WHERE Id=?;";
	//private final static String SQL_UPDATE_ROLE = "UPDATE users SET Roles_Id=? WHERE Id=?;";


	//private static final String SQL_SIGN_IN = "select u.*, r.DESCRIPTION as role from apptrainer.users u join apptrainer.roles r on u.Id = r.id where Login = ? and password = ?";


	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	static {
		//MYSQLDriverLoader.getInstance();
	}

/*
	try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false&serverTimezone=UTC",
					"root", "123456");

			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM news");

			news = new ArrayList<News>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String brief = rs.getString("brief");
				News n = new News(id, title, brief);

				news.add(n);

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
 */
	@Override
	public boolean registration(RegistrationInfo regInfo) throws Exception {
		//Proxy
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		boolean isRegistered = false;
		try {	connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1/apptrainer?useSSL=false&serverTimezone=UTC",
				"root", "778899");
			preparedStatement = connection.prepareStatement(SQL_CREATE_USER);
			String login = regInfo.getEmail();
			preparedStatement.setString(1, login);
			String password = regInfo.getPassword();
			//TODO: try to use standart MD5
			System.out.println(password+"pass2");
						password=new myMD5().md5Encode(password);
			System.out.println(password+"pass3");

			preparedStatement.setString(2, password);
			String name = regInfo.getName();
			preparedStatement.setString(3, name);
			String surname = regInfo.getSurname();
			preparedStatement.setString(4, surname);
			//TODO: decide who enter balance?
			//Integer balance=1000; BigDecimal.valueOf( balance).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
			String balance = "3.3";
			preparedStatement.setBigDecimal(5, new BigDecimal(balance));
			LocalDate date = LocalDateTime.now().toLocalDate();
			preparedStatement.setTimestamp(6, new java.sql.Timestamp(date.getYear()));
			isRegistered = true;
		} catch (SQLException e) {
			isRegistered = false;
			throw new Exception("Error in registration method", e);
		} finally {
			close(preparedStatement);
			if (connection != null) close(connection);
		}
//TODO:???
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

				//	usersList.add(buildUser(resultSet));
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

		password=new myMD5().md5Encode(password);
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
	//TODO: close connection!!!!
		//} finally {
	//	close(preparedStatement);
	//	if (connection != null)  close(connection);
	//}
		System.out.println("USER AUTHORIZATION");
		return user;
	}
}


