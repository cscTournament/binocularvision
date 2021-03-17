package by.gourianova.binocularvision.repository;

import java.sql.SQLException;


public class User extends BaseTable implements TableOperations{

    private String email;
    private String name;
    private String surname;
    private String password;

    public User(String name, String surname, String email, String password) throws SQLException {
         super(surname+"_table");
       this.email=email;
       this.name=name;
       this.surname=surname;
       this.password=password;
     }

     @Override
     public void createTable() throws SQLException {

        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS " +email+"_table("+
                 "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                name+" NVARCHAR NOT NULL,"  +
                surname+ "NVARCHAR NOT NULL,"  +
                email+ " NVARCHAR NOT NULL,"  +
                password+" NVARCHAR NOT NULL,"  +

                 ")",  "Создана таблица " + tableName);

     }
     @Override
     public void createForeignKeys() throws SQLException {
        //TODO: rewise
        super.executeSqlStatement(" ALTER TABLE"  +email+"_table(\"+ ADD FOREIGN KEY (password) REFERENCES users_table(password)",
                 "Cоздан внешний ключ user. -> user.id");
     }

     @Override
     public void createExtraConstraints() throws SQLException {

     }
 }
