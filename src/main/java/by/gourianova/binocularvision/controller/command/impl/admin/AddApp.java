package by.gourianova.binocularvision.controller.command.impl.admin;

import by.gourianova.binocularvision.bean.App;
import by.gourianova.binocularvision.controller.action.Router;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.AppService;
import by.gourianova.binocularvision.service.impl.AppServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.gourianova.binocularvision.util.PageOfConstants.APPS_PAGE;
import static by.gourianova.binocularvision.util.PageOfConstants.ERROR_PAGE;

public class AddApp implements Command {
    private final static String TYPE_ID = "typeId";
    private final static String HTTPADDRESS_ID = "httpAddressId";
    private final static String MESSAGE = "message";
    private AppService appService = new AppServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)  {//throws ServiceException, Exception{
        Router router = new Router();
        App app = new App();
        try {
            app.setTypeId(Integer.parseInt(request.getParameter(TYPE_ID)));
            app.setHttpAddressesId(Integer.parseInt(request.getParameter(HTTPADDRESS_ID)));
            appService.createApp(app);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(APPS_PAGE);
            requestDispatcher.forward(request, response);


        } catch (Exception e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }

    }
}
