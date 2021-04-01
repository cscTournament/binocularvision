package by.gourianova.binocularvision.service.impl;

import by.gourianova.binocularvision.bean.HttpAddress;
import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.impl.SQLHttpAddressDAO;
import by.gourianova.binocularvision.service.HttpAddressService;
import by.gourianova.binocularvision.service.ServiceException;

import java.util.ArrayList;

public class HttpAddressServiceImpl implements HttpAddressService {
  private SQLHttpAddressDAO httpAddressDao = new SQLHttpAddressDAO();

  @Override

  public ArrayList<HttpAddress> findAll() throws ServiceException {
      ArrayList<HttpAddress> httpAddresses;
        try {            httpAddresses= httpAddressDao.findAll();
        } catch (DAOException daoException) {
          throw new ServiceException("Transaction failed in findAll method", daoException);
        }
      return httpAddresses;}

    @Override
    public boolean createHttpAddress(HttpAddress httpAddress) throws ServiceException {
        try {
            return httpAddressDao.createEntity(httpAddress);
        } catch (DAOException e) {
            throw new ServiceException("Transaction failed in createStation method", e);
        }
    }

}
