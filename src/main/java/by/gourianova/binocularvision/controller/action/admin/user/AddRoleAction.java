package by.gourianova.binocularvision.controller.action.admin.user;

import by.gourianova.binocularvision.controller.action.*;//.Action;
import by.gourianova.binocularvision.controller.Router;
import by.gourianova.binocularvision.bean.Role;
import by.gourianova.binocularvision.service.RoleService;
import by.gourianova.binocularvision.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRoleAction implements Action {
    private final static String ROLE = "role";
    private final static String MESSAGE = "message";
    private RoleService roleService = new RoleService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        Role role = new Role();
        try {
            role.setRole(request.getParameter(ROLE));
            roleService.createRole(role);
            router.setPagePath(PageConstant.FIRST_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (by.gourianova.binocularvision.service.ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
