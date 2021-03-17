package by.gourianova.binocularvision.dao;

import by.gourianova.binocularvision.bean.Entity;
import by.gourianova.binocularvision.db.ConnectionPool;
import by.gourianova.binocularvision.db.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;


public abstract class AbstractDao<T extends Entity> {

    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    public abstract Collection<T> findAll() throws DAOException;

    public abstract T findEntityById(Integer id) throws DAOException;

    public abstract boolean createEntity(T entity) throws DAOException;

    public void close(ProxyConnection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
        }
    }

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
        }
    }
}
