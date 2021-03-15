package by.gourianova.binocularvision.dao;

import by.gourianova.binocularvision.dao.impl.SQLUserDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private final UserDAO userdao = new SQLUserDAO();
  //  private final NewsDAO newsDAO = new SQLNewsDAO();

    private DAOProvider() {}

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserdao() {
        return userdao;
    }

    public UserDAO getUserDao() {
        return userdao;
    }

    //  public NewsDAO getNewsDAO() {
  //     return newsDAO;
   // }
}
