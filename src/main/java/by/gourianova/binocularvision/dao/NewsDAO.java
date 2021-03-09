package by.gourianova.binocularvision.dao;

import by.gourianova.binocularvision.bean.News;

import java.util.List;

public interface NewsDAO {
	
	List<News> all()  throws DAOException;

}
