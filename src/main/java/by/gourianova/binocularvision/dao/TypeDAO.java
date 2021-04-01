package by.gourianova.binocularvision.dao;

import by.gourianova.binocularvision.bean.AppType;

public interface TypeDAO {
    AppType findEntityById(int typeId);
}
