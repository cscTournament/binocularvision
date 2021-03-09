package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.controller.command.*;
import by.gourianova.binocularvision.util.MD5;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class SaveNewUser implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String password=request.getParameter("password");
		password=new MD5().md5Encode(password);

				RegistrationInfo regInfo = new RegistrationInfo(request.getParameter("name"),request.getParameter("surname"),request.getParameter("email"),password);
		
		request.setAttribute("message", "Registration OK");



		log.println("class SaveNewUser implements Command "+request.getAttribute("message") +" with password: " + password);

		request.setAttribute("message", "Registration OK");


		
		response.sendRedirect("Controller?command=gotoindexpage&message="+request.getAttribute("message"));



		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		//requestDispatcher.forward(request, response);
		
	}

}
