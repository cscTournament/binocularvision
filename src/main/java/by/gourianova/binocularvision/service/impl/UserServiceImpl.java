package by.gourianova.binocularvision.service.impl;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.DAOProvider;
import by.gourianova.binocularvision.dao.UserDAO2;

import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService2;

public class UserServiceImpl implements UserService2 {
	
	@Override
	public User authorization(String login, String password) throws ServiceException {
		// validation
		if(login == null || "".equals(login) ) {// to bo cont....
			throw new ServiceException("wrong login or password");
		}
		
		DAOProvider provider = DAOProvider.getInstance();
        UserDAO2 userDAO = provider.getUserdao();
        
		User user = null;
		try {

			user = userDAO.authorization(login, password);
		}catch(DAOException e) {
			throw new ServiceException("error message", e);
		}
		return user;
	}

	@Override
	public boolean registration(RegistrationInfo regInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
