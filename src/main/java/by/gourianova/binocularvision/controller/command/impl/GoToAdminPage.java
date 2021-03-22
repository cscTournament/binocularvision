package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService;
import by.gourianova.binocularvision.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static by.gourianova.binocularvision.util.PageOfConstants.ADMIN_PAGE;
import static by.gourianova.binocularvision.util.PageOfConstants.ERROR_PAGE;

public class GoToAdminPage implements Command {
    private final static String USERS_LIST = "usersList";


    private final static String MESSAGE = "message";
    private UserService userService = new UserServiceImpl();


    @Override
    public  void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<User> usersList;

        try {
            usersList = userService.findAll();
            for(User user:usersList){
              //  log.println(user);
            }

            request.setAttribute(USERS_LIST, usersList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            e.printStackTrace();
        }

    }
}
