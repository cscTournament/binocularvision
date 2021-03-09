package by.gourianova.binocularvision.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MYSQLDriverLoader {
	private static final MYSQLDriverLoader instance = new MYSQLDriverLoader();

	static {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			System.out.println("Connection succesfull!");

			String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
			String username = "root";


			try (Connection con = DriverManager.getConnection(url, username, "778899")){

				Statement statement = con.createStatement();
				// создание таблицы
			     statement.executeUpdate("create database users;");

				System.out.println("Database has been created!");

			}


		}
		catch(Exception ex){
			System.out.println("Connection failed...");

			System.out.println(ex);
		}
	}

	
	private MYSQLDriverLoader() {}
	
	public static MYSQLDriverLoader getInstance() {
		return instance;
	}
}
