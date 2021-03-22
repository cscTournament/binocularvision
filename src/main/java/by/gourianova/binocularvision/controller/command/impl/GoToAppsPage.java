package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gourianova.binocularvision.util.PageOfConstants.*;

public class GoToAppsPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //TODO: make additional page
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(FIRST_PAGE);
            requestDispatcher.forward(request, response);

        } catch (//Service
                Exception e) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            e.printStackTrace();

        }


    }
}

