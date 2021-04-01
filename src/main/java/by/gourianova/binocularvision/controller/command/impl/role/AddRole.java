package by.gourianova.binocularvision.controller.command.impl.role;

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

import static by.gourianova.binocularvision.util.PageOfConstants.*;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class AddRole implements Command {
    private final static String ROLE = "role";
    //TODO: in pageConstant?
    private final static String MESSAGE = "message";
    private final RoleService roleService = new RoleServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role role = new Role();
        log.println("AddRole.java new role OK");
        role.setRole(request.getParameter(ROLE));
        log.println(role.getRole()+" role ");
        try {
            roleService.createRole(role);
            //TODO: revise
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADD_ROLE_PAGE);
        requestDispatcher.forward(request, response);
            log.println(" createRole ok");
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            e.printStackTrace();

        }


    }
}
