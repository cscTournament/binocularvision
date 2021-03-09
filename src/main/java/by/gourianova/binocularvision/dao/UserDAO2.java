package by.gourianova.binocularvision.dao;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.db.ConnectionPool;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public interface UserDAO2 {


    User authorization(String login, String password) throws DAOException;

    boolean registration(RegistrationInfo regInfo) throws DAOException;

}
