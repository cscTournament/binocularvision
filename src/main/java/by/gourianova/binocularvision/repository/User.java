package by.gourianova.binocularvision.repository;

import java.sql.SQLException;


public class User extends BaseTable implements TableOperations{

     public User() throws SQLException {
         super("user_table");
     }

     @Override
     public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS user_table("+
                 "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                 "name NVARCHAR NOT NULL,"  +
                  "surname NVARCHAR NOT NULL,"  +
                  "email NVARCHAR NOT NULL,"  +
                   "password NVARCHAR NOT NULL,"  +

                 ")",  "Создана таблица " + tableName);

     }
     @Override
     public void createForeignKeys() throws SQLException {
        super.executeSqlStatement(" ALTER TABLE oven_table ADD FOREIGN KEY (password) REFERENCES user_table(surname)",
                 "Cоздан внешний ключ appliance. -> appliance.id");
     }

     @Override
     public void createExtraConstraints() throws SQLException {

     }
 }
