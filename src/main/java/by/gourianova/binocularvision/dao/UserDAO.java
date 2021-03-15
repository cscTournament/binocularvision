package by.gourianova.binocularvision.dao;


import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.dao.impl.DAOException;

import java.util.ArrayList;


public interface UserDAO {


    User authorization(String login, String password) throws DAOException2;//, by.gourianova.binocularvision.dao.impl.DAOException;

     boolean registration(RegistrationInfo regInfo) throws DAOException;

//    boolean registration(RegistrationInfo regInfo) throws by.gourianova.binocularvision.dao.impl.DAOException;

    ArrayList<User> findAll() throws Exception;

}
