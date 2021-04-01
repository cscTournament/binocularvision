package by.gourianova.binocularvision.service.impl;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.bean.UserChangeInfo;
import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.DAOProvider;
import by.gourianova.binocularvision.dao.UserDAO;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService;

import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


public class UserServiceImpl implements UserService {
    DAOProvider provider = DAOProvider.getInstance();
    UserDAO userDAO = provider.getUserDao();

    @Override
    public User authorization(String login, String password) throws ServiceException {
        // validation
        if (login == null || "".equals(login)) {// to bo cont....
            throw new ServiceException("wrong login or password");
        }


        //	password = new MD5().md5Encode(password);
        //TODO where put MD5?
        log.println("password NOT NUL  UserServiceImpl.authorization");

        User user = null;
        log.println("try to find user with " + login + " login and " + password + " password ");

        try {
            user = userDAO.authorization(login, password);

        } catch (DAOException daoException) {
            daoException.printStackTrace();
        }


        if (user != null) log.println(" USER NOT NUL  UserServiceImpl.authorization");
        return user;

    }

    @Override
    public ArrayList<User> findAll() throws ServiceException {

        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDao();

        ArrayList<User> users;

        try {
            users = userDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return users;
    }

    @Override
    public boolean registration(RegistrationInfo regInfo) {
        boolean isRegistered = false;

        if (regInfo != null) {

            try {
                isRegistered = userDAO.registration(regInfo);
            } catch (Exception e) {
                System.out.println("Smth wrong with registration at SQLUserDAO/UserServiceImpl");
                e.printStackTrace();

            }
            if (isRegistered)
                System.out.println("UserServiceImpl OK, RegistrationInfo  != null");

        }

        return isRegistered;
    }

    @Override
    public User findUserById(Integer id) throws ServiceException {
        User user = null;
        log.println("try to find user with " + id + " id ");
        try {
            user = userDAO.findUserById(id);
        } catch (DAOException e) {
            throw new ServiceException("Transaction failed in findUserById method", e);
        }
        if (user != null) log.println(" USER NOT NUL  UserServiceImpl.findUserById");
        return user;
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        try {
            userDAO.updateUser(user);
        } catch (DAOException e) {
            throw new ServiceException("Transaction failed in updateUser method", e);
        }
    }

    @Override
    public boolean updateUserData(UserChangeInfo userloginInfo) throws ServiceException {
        boolean isUpdated = false;
        try {
            isUpdated = userDAO.updateUserData(userloginInfo);
        } catch (DAOException e) {
            throw new ServiceException("Transaction failed in updateUserData method", e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteUserById(Integer id) throws ServiceException {
        boolean isDeleted = false;
        try {
            isDeleted = userDAO.deleteUserById(id);
        } catch (DAOException e) {
            throw new ServiceException("Transaction failed in deleteUser method", e);
        }

        return isDeleted;
    }

}