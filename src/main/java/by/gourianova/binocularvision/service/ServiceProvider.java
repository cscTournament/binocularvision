package by.gourianova.binocularvision.service;

//import by.gourianova.binocularvision.service.impl.NewsServiceImpl;
import by.gourianova.binocularvision.service.impl.UserServiceImpl;


public final class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider(); 
	private ServiceProvider() {}
	private final UserService2 userService2 = new UserServiceImpl();


	//	private final NewsService newsService = new NewsServiceImpl();
	
	public static ServiceProvider getInstance() {
		return instance;
	}

    public UserService2 getUserService2() {
		return userService2;
	}


	//public NewsService getNewsService() {
//		return newsService;
//	}
	
}
