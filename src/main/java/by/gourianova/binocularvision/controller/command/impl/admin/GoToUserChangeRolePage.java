package by.gourianova.binocularvision.controller.command.impl.admin;

import by.gourianova.binocularvision.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.gourianova.binocularvision.util.PageOfConstants.CHANGE_USER_ROLE_PAGE;
import static by.gourianova.binocularvision.util.PageOfConstants.ERROR_PAGE;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class GoToUserChangeRolePage implements Command {
    private final static String MESSAGE = "message";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(CHANGE_USER_ROLE_PAGE);
          // TODO:fix
            log.println(request.getContextPath() + " request.getContextPath() ");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            e.printStackTrace();
        }
    }
}

