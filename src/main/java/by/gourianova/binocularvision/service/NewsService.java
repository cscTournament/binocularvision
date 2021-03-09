package by.gourianova.binocularvision.service;

import by.gourianova.binocularvision.bean.News;

import java.util.List;

public interface NewsService {
	List<News> takeAll() throws ServiceException;
}
