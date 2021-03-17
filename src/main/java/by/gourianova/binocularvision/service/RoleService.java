package by.gourianova.binocularvision.service;

import by.gourianova.binocularvision.bean.Role;
import by.gourianova.binocularvision.bean.User;

import java.util.ArrayList;


public  interface RoleService {


    ArrayList<User> takeAll() throws ServiceException;

    public boolean createRole(Role role) throws ServiceException;

    public Role findEntityById(Integer id) throws ServiceException;

}

