package by.gourianova.binocularvision.service.impl;

import by.gourianova.binocularvision.bean.News;
import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.DAOProvider;
import by.gourianova.binocularvision.dao.NewsDAO;
import by.gourianova.binocularvision.service.NewsService;
import by.gourianova.binocularvision.service.ServiceException;

import java.util.List;

public class NewsServiceImpl implements NewsService {

	@Override
	public List<News> takeAll() throws ServiceException {

		DAOProvider provider = DAOProvider.getInstance();
		NewsDAO newsDAO = provider.getNewsDAO();
		
		List<News> news;
		try {
			news = newsDAO.all();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return news;
	}
}
