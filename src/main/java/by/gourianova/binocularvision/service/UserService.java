package by.gourianova.binocularvision.service;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.bean.UserChangeInfo;

import java.util.ArrayList;

public interface UserService {
	User authorization(String login, String password) throws ServiceException;

	ArrayList<User> findAll() throws ServiceException;

	boolean registration(RegistrationInfo regInfo) throws ServiceException;

    User findUserById(Integer id) throws ServiceException;

    void updateUser(User user) throws ServiceException;

    boolean updateUserData(UserChangeInfo userloginInfo)throws ServiceException;

    boolean deleteUserById(Integer id) throws ServiceException;
}
