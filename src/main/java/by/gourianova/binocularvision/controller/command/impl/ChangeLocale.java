package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gourianova.binocularvision.util.PageOfConstants.ERROR_PAGE;


public class ChangeLocale implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ChangeLocaleAction");
            requestDispatcher.forward(request, response);

        } catch (
                Exception e) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
            e.printStackTrace();
        }


    }
}
