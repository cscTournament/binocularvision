package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gourianova.binocularvision.util.PageConstant.REGISTRATION_PAGE;

public class GoToRegistrationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //TODO: move to WEB+INF for safe?
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(REGISTRATION_PAGE);
		requestDispatcher.forward(request, response);

	}

}
