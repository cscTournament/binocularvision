package by.gourianova.binocularvision.controller.command.impl;

//import by.gourianova.binocularvision.bean.News;

import by.gourianova.binocularvision.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gourianova.binocularvision.util.PageOfConstants.ERROR_PAGE;
import static by.gourianova.binocularvision.util.PageOfConstants.LOGIN_PAGE;

public class GoToLoginPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
			requestDispatcher.forward(request, response);
			
		} catch (//Service
				Exception e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
			e.printStackTrace();
		}	
	

	}

}
