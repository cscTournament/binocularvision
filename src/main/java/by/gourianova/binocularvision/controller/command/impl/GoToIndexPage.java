package by.gourianova.binocularvision.controller.command.impl;

//import by.gourianova.binocularvision.bean.News;
import by.gourianova.binocularvision.controller.command.Command;
//import by.gourianova.binocularvision.service.NewsService;
//import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToIndexPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
//		NewsService newsService = provider.getNewsService();
		
		try {
//			List<News> news = newsService.takeAll();
			
	//		request.setAttribute("news", news);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ListOfApps/jsp/main_index.jsp");
			requestDispatcher.forward(request, response);
			
		} catch (//Service
				Exception e) {
			// TODO перейти на глобальную страницу ошибок
			e.printStackTrace();
		}	
	

	}

}
