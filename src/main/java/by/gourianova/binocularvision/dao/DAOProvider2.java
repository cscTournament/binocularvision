package by.gourianova.binocularvision.dao;

import by.gourianova.binocularvision.dao.impl.SQLNewsDAO;
import by.gourianova.binocularvision.dao.impl.SQLUserDAO;

public class DAOProvider2 {
    private static final DAOProvider2 instance = new DAOProvider2();

    private final UserDAO2 userdao = new SQLUserDAO();
    private final NewsDAO newsDAO = new SQLNewsDAO();

    private DAOProvider2() {}

    public static DAOProvider2 getInstance() {
        return instance;
    }

    public UserDAO2 getUserdao() {
        return userdao;
    }

    public UserDAO2 getUserDao() {
        return userdao;
    }

    //  public NewsDAO getNewsDAO() {
  //     return newsDAO;
   // }
}
