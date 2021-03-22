package by.gourianova.binocularvision.service.impl;

import by.gourianova.binocularvision.bean.Role;
import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.impl.SQLRoleDAO;
import by.gourianova.binocularvision.service.RoleService;
import by.gourianova.binocularvision.service.ServiceException;

import java.util.ArrayList;

public class RoleServiceImpl implements RoleService {

    private SQLRoleDAO roleDao = new SQLRoleDAO();

    @Override
    public ArrayList<Role> findAll() throws ServiceException {
        try {
            return roleDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
    }
    @Override
    public boolean createRole(Role role) throws ServiceException {
        try {
            return roleDao.createRole(role);
        } catch (DAOException e) {
            throw new ServiceException("Transaction failed in createRole method", e);
        }
    }

}



