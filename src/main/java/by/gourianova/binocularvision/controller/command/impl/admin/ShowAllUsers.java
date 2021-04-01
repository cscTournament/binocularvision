package by.gourianova.binocularvision.controller.command.impl.admin;

import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService;
import by.gourianova.binocularvision.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static by.gourianova.binocularvision.util.PageOfConstants.ALL_USERS;
import static by.gourianova.binocularvision.util.PageOfConstants.ERROR_PAGE;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


public class ShowAllUsers implements Command {
    private final static String USERS_LIST = "usersList";
    private final static String MESSAGE = "message";
    private UserService userService = new UserServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


        ArrayList<User> usersList;
        try {
            usersList = userService.findAll();
            request.setAttribute(USERS_LIST, usersList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ALL_USERS);
            requestDispatcher.forward(request, response);
            log.println(" ShowAllUsers ok");

        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }




    }

}

