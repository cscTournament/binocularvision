package by.gourianova.binocularvision.service;

import by.gourianova.binocularvision.bean.Order;
import by.gourianova.binocularvision.bean.User;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface OrderService {


    BigDecimal calculateOrder(User user) throws ServiceException;

    void closeOrder(User user) throws ServiceException;

    Order findEntityById(Integer id) throws ServiceException;

    ArrayList<Order> findAllUserOrders(Integer userId) throws ServiceException;

    ArrayList<Order> findAll() throws ServiceException;

    ArrayList<Order> findUnclosed() throws ServiceException;
}
