package by.gourianova.binocularvision.dao;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;


import java.sql.SQLException;
import java.util.Collection;


public interface UserDAO {


    User authorization(String login, String password) throws DAOException, by.gourianova.binocularvision.dao.impl.DAOException;

    boolean registration(RegistrationInfo regInfo) throws Exception;

    Collection<User> findAll() throws Exception;

}
