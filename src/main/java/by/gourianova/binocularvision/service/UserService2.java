package by.gourianova.binocularvision.service;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;

public interface UserService2 {
    User authorization(String login, String passport) throws ServiceException;
    boolean registration(RegistrationInfo regInfo) throws ServiceException;

}
