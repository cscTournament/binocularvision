package by.gourianova.binocularvision.service;


import by.gourianova.binocularvision.bean.HttpAddress;

import java.util.ArrayList;

public interface  HttpAddressService {
    ArrayList<HttpAddress> findAll() throws ServiceException;

    boolean createHttpAddress(HttpAddress httpAddress) throws ServiceException;
}
