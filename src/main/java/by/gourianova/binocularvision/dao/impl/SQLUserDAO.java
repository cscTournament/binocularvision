package by.gourianova.binocularvision.dao.impl;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;

import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.UserDAO2;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class SQLUserDAO implements UserDAO2{

	static {
		MYSQLDriverLoader.getInstance();
	}

	@Override
	public User authorization(String login, String password) throws DAOException {

       log.println("USER AUTHORIZATION");
		
       return new User();
	}

	@Override
	public boolean registration(RegistrationInfo regInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
