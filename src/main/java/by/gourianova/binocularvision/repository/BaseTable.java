package by.gourianova.binocularvision.repository;



//import sql.demo.StockExchangeDB;

import by.gourianova.binocularvision.db.ExchangeDB;

import java.io.Closeable;
import java.sql.*;

// Сервисный родительский класс, куда вынесена реализация общих действий для всех таблиц
public class BaseTable implements Closeable {
    Connection connection;  // JDBC-соединение для работы с таблицей
    String tableName;       // Имя таблицы

    BaseTable(String tableName) throws SQLException { // Для реальной таблицы передадим в конструктор её имя
        this.tableName = tableName;
        //TODO:fix or redone
  //      this.connection = ExchangeDB.getConnection(); // Установим соединение с СУБД для дальнейшей работы
    }

    // Закрытие
    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения!");
        }
    }

    // Выполнить SQL команду без параметров в СУБД, по завершению выдать сообщение в консоль
    void executeSqlStatement(String sql, String description) throws SQLException {
        reopenConnection(); // переоткрываем (если оно неактивно) соединение с СУБД
        Statement statement = connection.createStatement();  // Создаем statement для выполнения sql-команд
        statement.execute(sql); // Выполняем statement - sql команду
        statement.close();      // Закрываем statement для фиксации изменений в СУБД
        if (description != null)
            System.out.println(description);
    };

    void executeSqlStatement(String sql) throws SQLException {
        executeSqlStatement(sql, null);
    };


    // Активизация соединения с СУБД, если оно не активно.
    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            //TODO:fix or redone
        //    connection = ExchangeDB.getConnection();
        }
    }

    public static class SharesDB extends BaseTable implements TableOperations {

        public SharesDB() throws SQLException {
            super("appliances");
      }



           @Override
                public void createTable() throws SQLException {
                   super.executeSqlStatement("CREATE TABLE appliances(" +
                            "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                            "name VARCHAR(255) NOT NULL," +
                            "startPrice DECIMAL(15,2) NOT NULL DEFAULT 10," +
                            "changeProbability INTEGER NOT NULL DEFAULT 25,"+
                            "delta INTEGER NOT NULL DEFAULT 15)", "Создана таблица " + tableName);

                }


        @Override
        public void createForeignKeys() throws SQLException {
            super.executeSqlStatement(" ALTER TABLE appliances ADD FOREIGN KEY (id) REFERENCES appliances(id)",
                    "Cоздан внешний ключ appliances. -> appliances.id");
        }


        @Override
        public void createExtraConstraints() throws SQLException {
            super.executeSqlStatement(
                    " ALTER TABLE shares ADD CONSTRAINT appliances CHECK(delta <= 100 and delta > 0)",
                    "Cоздано ограничение для appliances, поле delta = [1,100]");
            super.executeSqlStatement(
                    " ALTER TABLE shares ADD CONSTRAINT check_appliances_changeProbability " +
                            "CHECK(changeProbability <= 100 and changeProbability > 0)",
                    "Cоздано ограничение для appliances, поле changeProbability = 1..100");

        }

    }
}
