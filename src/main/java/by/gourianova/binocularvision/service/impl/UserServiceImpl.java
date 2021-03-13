package by.gourianova.binocularvision.service.impl;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.dao.DAOException;

import by.gourianova.binocularvision.dao.DAOProvider2;
import by.gourianova.binocularvision.dao.UserDAO2;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Override
	public User authorization(String login, String password) throws ServiceException {
		// validation
		if(login == null || "".equals(login) ) {// to bo cont....
			throw new ServiceException("wrong login or password");
		}

		DAOProvider2 provider = DAOProvider2.getInstance();
        UserDAO2 userDAO = provider.getUserDao();
        
		User user = null;
		try {
			user = userDAO.authorization(login, password);
		}catch(DAOException e) {
			throw new ServiceException("error message", e);
		} catch (by.gourianova.binocularvision.dao.impl.DAOException e) {
			System.out.println("Tmth wrong with authorization");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean registration(RegistrationInfo regInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
