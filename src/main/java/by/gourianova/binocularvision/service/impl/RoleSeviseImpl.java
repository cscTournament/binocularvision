package by.gourianova.binocularvision.service.impl;

import by.gourianova.binocularvision.bean.Role;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.service.RoleService;
import by.gourianova.binocularvision.service.ServiceException;

import java.util.ArrayList;

public class RoleSeviseImpl implements RoleService {
    @Override
    public ArrayList<User> takeAll() throws ServiceException {
        return null;
    }

    @Override
    public boolean createRole(Role role) throws ServiceException {
        return false;
    }

    @Override
    public Role findEntityById(Integer id) throws ServiceException {
        return null;
    }

 /*   private RoleDAO roleDao = new RoleDAO();

        @Override
        public ArrayList<Role> findAll() throws ServiceException {
            return null;
        }

        @Override
        public Role findEntityById(Integer id) throws ServiceException {
            return null;
        }

    @Override
    public boolean createRole(Role role) throws ServiceException {
        boolean isCreated = false;
        if (role != null)
        try {
            return roleDao.createRole(role);
        } catch (DAOException e) {
            throw new ServiceException("Smth wrong with registration at SQLRoleDAO/RoleServiceImpl", e);
            e.printStackTrace();
        }
   return isCreated;
    }
*/

}



