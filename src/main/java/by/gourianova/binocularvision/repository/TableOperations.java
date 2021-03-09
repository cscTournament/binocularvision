package by.gourianova.binocularvision.repository;

import java.sql.SQLException;
// Операции с таблицами

public interface TableOperations {
    void createTable() throws SQLException; // создание таблицы

    void createForeignKeys() throws SQLException; // создание связей между таблицами

    void createExtraConstraints() throws SQLException; // создание дополнительных правил для значений полей таблиц

}


