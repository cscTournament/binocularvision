package by.gourianova.binocularvision.dao;

import by.gourianova.binocularvision.bean.Role;

import java.util.ArrayList;


public interface  RoleDAO {

    public ArrayList<Role> findAll() throws DAOException;

    public Role findEntityById(Integer id) throws DAOException;

    public  boolean createRole(Role role) throws DAOException;
}
