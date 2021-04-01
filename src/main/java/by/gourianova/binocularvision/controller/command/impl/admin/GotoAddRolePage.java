package by.gourianova.binocularvision.controller.command.impl.admin;

import by.gourianova.binocularvision.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.gourianova.binocularvision.util.PageOfConstants.*;

public class GotoAddRolePage implements Command {
    private final static String MESSAGE = "message";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADD_ROLE_PAGE);
        requestDispatcher.forward(request, response);
    } catch ( Exception e) {
        request.getSession().setAttribute(MESSAGE, e.getMessage());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
        e.printStackTrace();
    }
    }
}
