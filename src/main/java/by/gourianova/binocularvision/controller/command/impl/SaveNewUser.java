package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.controller.command.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveNewUser implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getParameter("name");
		//request.getParameter("surname");
		
		RegistrationInfo regInfo = new RegistrationInfo();
		
		//regInfo - show in console
		System.out.println("class SaveNewUser implements Command");
		
		//request.setAttribute("message", "Registration OK");
		
		response.sendRedirect("Controller?command=gotoindexpage&message=Registration ok");
		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		//requestDispatcher.forward(request, response);
		
	}

}
