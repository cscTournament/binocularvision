package by.gourianova.binocularvision.controller.command.impl.app;

import by.gourianova.binocularvision.bean.AppType;
import by.gourianova.binocularvision.bean.HttpAddress;
import by.gourianova.binocularvision.controller.action.Router;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.AppTypeService;
import by.gourianova.binocularvision.service.HttpAddressService;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.impl.AppTypeServiceImpl;
import by.gourianova.binocularvision.service.impl.HttpAddressServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static by.gourianova.binocularvision.util.PageOfConstants.ADD_APP;
import static by.gourianova.binocularvision.util.PageOfConstants.ERROR_PAGE;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class GetAppData implements Command {
    private final static String TYPES_LIST = "typesList";
    private final static String HTTPADDRESSES_LIST = "stationsList";
    private final static String MESSAGE = "message";
    private AppTypeService appTypeService = new AppTypeServiceImpl();
    private HttpAddressService httpAddressService = new HttpAddressServiceImpl();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      {
            Router router = new Router();
            ArrayList<AppType> typesList;
            ArrayList<HttpAddress> stationsList;

            try {
                typesList = appTypeService.findAll();
                stationsList = httpAddressService.findAll();
                request.setAttribute(TYPES_LIST, typesList);
                request.setAttribute(HTTPADDRESSES_LIST, stationsList);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADD_APP);
                requestDispatcher.forward(request, response);
                log.println(" GetAppData ok");


            } catch (ServiceException e) {
                request.getSession().setAttribute(MESSAGE, e.getMessage());
                router.setPagePath(ERROR_PAGE);
                router.setRoute(Router.RouteType.REDIRECT);
            }

        }
    }
}
