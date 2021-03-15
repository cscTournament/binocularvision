package by.gourianova.binocularvision.dao;

import by.gourianova.binocularvision.bean.Role;

import java.util.ArrayList;


public interface  RoleDAO {

    public ArrayList<Role> findAll() throws DAOException2;

    public Role findEntityById(Integer id) throws DAOException2;


    public boolean createEntity(Role entity) throws DAOException2;
}
