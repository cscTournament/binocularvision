package by.gourianova.binocularvision.dao;

import by.gourianova.binocularvision.bean.App;
import by.gourianova.binocularvision.bean.Order;
import by.gourianova.binocularvision.bean.User;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface OrderDAO {
    boolean createEntity(Order entity) throws DAOException;

    ArrayList<Order> findAll() throws DAOException;

    Order findEntityById(Integer userId) throws DAOException;

    ArrayList<Order> findAllUserOrders(Integer userId) throws DAOException;

    Order findUnclosedOrder(Integer userId) throws DAOException;

    ArrayList<Order> findUnclosed() throws DAOException;

    void closeOrder (User user, BigDecimal value, App app) throws DAOException;
}
