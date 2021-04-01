package by.gourianova.binocularvision.controller.command.impl.admin;

import by.gourianova.binocularvision.bean.Role;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.RoleService;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.impl.RoleServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static by.gourianova.binocularvision.util.PageOfConstants.ALL_ROLES;
import static by.gourianova.binocularvision.util.PageOfConstants.ERROR_PAGE;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class ShowAllRoles implements Command {
    private final static String ROLES_LIST = "rolesList";
    private final static String MESSAGE = "message";
    private RoleService roleService = new RoleServiceImpl();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ArrayList<Role> rolesList;

        try {
            rolesList = roleService.findAll();
            request.setAttribute(ROLES_LIST, rolesList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ALL_ROLES);
            try {
                requestDispatcher.forward(request, response);
                log.println(" ShowAllRoles ok");
            } catch (IOException e) {
                log.println(" ShowAllRoles failed");
                e.printStackTrace();
            }

        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            try {
                requestDispatcher.forward(request, response);
            } catch (IOException ioException) {
                log.println(" failed load error_page");
                ioException.printStackTrace();
            }
        }
    }
}




