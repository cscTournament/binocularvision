package by.gourianova.binocularvision.db;



//TODO:fix or redone

import by.gourianova.binocularvision.util.ConfigurationManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;



public class ExchangeDB {
        //TODO: rewrite for site

        public static void main(String[] args) {
            String url = ConfigurationManager.getProperty("dburl");
            String user = ConfigurationManager.getProperty("dbuser");
            String password = ConfigurationManager.getProperty("dbpassword");

               Connection connection =null;
               Statement statement=null;

                LocalDate date = LocalDateTime.now().toLocalDate();
                System.out.println(date + "\n");
                try {

                       connection = DriverManager.getConnection(url,user,password);

                   statement = connection.createStatement();

                        statement.executeUpdate("create table if not exists users(id INT(11) NOT NULL auto_increment," +
                                "Login varchar(10) not null unique , Password varchar(32) not null,First_Name varchar(15) not null, " +
                                "Last_Name varchar(15) not null, Balance DECIMAL (6,2), Create_time DATETIME,  primary key (id) );");

                        Object param = new java.sql.Timestamp(date.getYear());
                        PreparedStatement preparedStatement = connection.prepareStatement("insert into users SET Create_time=?");
                        preparedStatement.setObject(1, param);
                        statement.executeUpdate("insert into users (Login,Password,First_Name,Last_Name,Balance) values ('tgour','qwe','Tatiana','Gourianova','3.3')");

                        ResultSet resultSet = statement.executeQuery("SELECT *FROM users");
                        while (resultSet.next()) {
                                for (int i = 1; i <= 7; i++)
                                System.out.print(resultSet.getString(i) + " ");
                               System.out.println();

                        }



                } catch (
                        SQLException e) {
                        e.printStackTrace();

                } finally {
                        try {if (statement!=null)
                                statement.close();
                        } catch (SQLException throwables) {
                                throwables.printStackTrace();
                        }
                        try { if (connection != null)
                            connection.close();
                        } catch (SQLException throwables) {
                                throwables.printStackTrace();
                        }

                }

        }
}



