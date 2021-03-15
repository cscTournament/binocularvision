package by.gourianova.binocularvision.service.impl;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.dao.DAOException;

import by.gourianova.binocularvision.dao.DAOProvider;
import by.gourianova.binocularvision.dao.UserDAO;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService;
import by.gourianova.binocularvision.util.MD5;


public class UserServiceImpl implements UserService {

	@Override
	public User authorization(String login, String password) throws ServiceException {
		// validation
		if (login == null || "".equals(login)) {// to bo cont....
			throw new ServiceException("wrong login or password");
		}

		DAOProvider provider = DAOProvider.getInstance();
		UserDAO userDAO = provider.getUserDao();
		//password = new MD5().md5Encode(password);
		//TODO MD5?
		System.out.println(password + "password usimpl");
		User user = null;
		try {
			user = userDAO.authorization(login, password);
		} catch (DAOException e) {
			throw new ServiceException("error message", e);
		} catch (by.gourianova.binocularvision.dao.impl.DAOException e) {
			System.out.println("Smth wrong with authorization at SQLUserDAO/UserServiceImpl");
			e.printStackTrace();
		}
		if (user != null) System.out.println("usimpl");
		return user;

	}

	@Override
	public boolean registration(RegistrationInfo regInfo) {
		boolean isRegistered = false;

		if (regInfo != null) {

			DAOProvider provider = DAOProvider.getInstance();
			UserDAO userDAO = provider.getUserDao();


			try {
				isRegistered = userDAO.registration(regInfo);
			} catch (Exception e) {
				System.out.println("Smth wrong with registration at SQLUserDAO/UserServiceImpl");
				e.printStackTrace();

			}
			if (isRegistered)
				System.out.println("UserServiceImpl OK, RegistrationInfo  != null");


		}

		return isRegistered;}
}
