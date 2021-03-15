package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToRegistrationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     //TODO: move to WEB+INF for safe?
	//	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/registration.jsp");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/user_register.jsp");


		requestDispatcher.forward(request, response);
		
	}

}
