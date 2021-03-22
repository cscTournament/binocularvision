package by.gourianova.binocularvision.controller.command.impl.admin;

import by.gourianova.binocularvision.bean.Role;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.RoleService;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService;
import by.gourianova.binocularvision.service.impl.RoleServiceImpl;
import by.gourianova.binocularvision.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static by.gourianova.binocularvision.util.PageOfConstants.ERROR_PAGE;
import static by.gourianova.binocularvision.util.PageOfConstants.UPDATE_USER;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class GetUserData implements Command {
    private final static String USERS_LIST = "usersList";
    private final static String ROLES_LIST = "rolesList";
    private final static String MESSAGE = "message";
    private UserService userService = new UserServiceImpl();
    private RoleService roleService = new RoleServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> usersList;
        ArrayList<Role> rolesList;

        try {
            usersList = userService.findAll();
            rolesList = roleService.findAll();
            request.setAttribute(USERS_LIST, usersList);
            request.setAttribute(ROLES_LIST, rolesList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(UPDATE_USER);
            log.println(" GetUserData ok");
        } catch (ServiceException e) {

            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }

    }
}