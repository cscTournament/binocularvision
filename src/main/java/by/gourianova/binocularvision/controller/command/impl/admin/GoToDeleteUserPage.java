package by.gourianova.binocularvision.controller.command.impl.admin;

import by.gourianova.binocularvision.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.gourianova.binocularvision.util.PageOfConstants.*;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.apache.logging.log4j.core.impl.ThrowableFormatOptions.MESSAGE;

public class GoToDeleteUserPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(DELETE_USER_PAGE);
            log.println("GoToDeleteUserPage OK ");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            e.printStackTrace();
        }
    }
}
