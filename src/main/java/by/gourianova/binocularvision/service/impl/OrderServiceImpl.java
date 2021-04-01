package by.gourianova.binocularvision.service.impl;

import by.gourianova.binocularvision.bean.App;
import by.gourianova.binocularvision.bean.AppType;
import by.gourianova.binocularvision.bean.Order;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.impl.SQLAppDAO;
import by.gourianova.binocularvision.dao.impl.SQLOrderDAO;
import by.gourianova.binocularvision.dao.impl.SQLTypeDAO;
import by.gourianova.binocularvision.service.OrderService;
import by.gourianova.binocularvision.service.ServiceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class OrderServiceImpl implements OrderService {
    private SQLOrderDAO orderDao = new SQLOrderDAO();
    private SQLAppDAO appDao = new SQLAppDAO();
    private SQLTypeDAO typeDao=new SQLTypeDAO();

    public boolean checkBalance(User user) throws ServiceException {
        BigDecimal value = calculateOrder(user);
        return user.getBalance().intValue() >= value.intValue();
    }


    @Override
    public BigDecimal calculateOrder(User user) throws ServiceException {
        Order order;
        App app;
        AppType type;
        BigDecimal value;
        try {
            order = orderDao.findUnclosedOrder(user.getId());
            LocalDateTime startRent = order.getStartRent();
            LocalDateTime endRent = LocalDateTime.now();
            BigDecimal minutes = new BigDecimal(ChronoUnit.MINUTES.between(startRent, endRent));
            app = appDao.findEntityById(order.getAppId());
            type=typeDao.findEntityById(app.getTypeId());
            BigDecimal pricePerMinutes = type.getPricePerHour();
            value = minutes.multiply(pricePerMinutes);
        } catch (Exception e) {
            throw new ServiceException("Transaction failed in calculateOrder method", e);
        }
        return value;
    }
    @Override
    public void closeOrder(User user) throws ServiceException {
        Order order;
        App app;
        AppType type;
        try {
            order = orderDao.findUnclosedOrder(user.getId());
            LocalDateTime startRent = order.getStartRent();
            LocalDateTime endRent = LocalDateTime.now();
            BigDecimal minutes = new BigDecimal(ChronoUnit.MINUTES.between(startRent, endRent));
            app = appDao.findEntityById(order.getAppId());
            type=typeDao.findEntityById(app.getTypeId());
            BigDecimal pricePerMinutes = type.getPricePerHour();
            BigDecimal value = minutes.multiply(pricePerMinutes);
            orderDao.closeOrder(user, value, app);
        } catch (Exception e) {
            throw new ServiceException("Transaction failed in closeOrder method", e);
        }
    }
    @Override
    public Order findEntityById(Integer id) throws ServiceException {
        try {
            return orderDao.findEntityById(id);
        } catch (DAOException e) {
            throw new ServiceException("Transaction failed in findEntityById method", e);
        }
    }
    @Override
    public ArrayList<Order> findAllUserOrders(Integer userId) throws ServiceException {
        try {
            return orderDao.findAllUserOrders(userId);
        } catch (Exception e) {
            throw new ServiceException("Transaction failed in findAllUserOrders method", e);
        }
    }
    @Override
    public ArrayList<Order> findAll() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
    }
    @Override
    public ArrayList<Order> findUnclosed() throws ServiceException {
        try {
            return orderDao.findUnclosed();
        } catch (Exception e) {
            throw new ServiceException("Transaction failed in findUnclosed method", e);
        }
    }
}
