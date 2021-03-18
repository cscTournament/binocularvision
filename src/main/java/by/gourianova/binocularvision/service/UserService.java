package by.gourianova.binocularvision.service;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;

import java.util.ArrayList;

public interface UserService {
	User authorization(String login, String password) throws ServiceException;

	ArrayList<User> takeAll() throws ServiceException;

	boolean registration(RegistrationInfo regInfo) throws ServiceException;
}
