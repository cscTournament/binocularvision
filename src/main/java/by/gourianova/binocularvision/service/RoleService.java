package by.gourianova.binocularvision.service;

import by.gourianova.binocularvision.bean.Role;

import java.util.ArrayList;


public  interface RoleService {



    public ArrayList<Role> findAll() throws ServiceException;

    public boolean createRole(Role role) throws ServiceException;

}

