package by.gourianova.binocularvision.dao;


import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.bean.UserChangeInfo;

import java.util.ArrayList;


public interface UserDAO {


    User authorization(String login, String password) throws DAOException;

     boolean registration(RegistrationInfo regInfo) throws DAOException;


    ArrayList<User> findAll() throws DAOException;

    User findUserById(Integer id) throws DAOException;


    void updateUser(User user) throws DAOException;

    boolean updateUserData(UserChangeInfo userloginInfo) throws DAOException;

   // boolean delete(User user) throws DAOException;

    boolean deleteUserById(Integer id) throws DAOException;
}
