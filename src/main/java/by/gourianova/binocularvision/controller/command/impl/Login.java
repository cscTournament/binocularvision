package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService;
import by.gourianova.binocularvision.service.impl.UserServiceImpl;
import by.gourianova.binocularvision.util.MD5;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String password;
		String login = request.getParameter("login");
		password = request.getParameter("password");
		password= MD5.md5Encode(password);

		//ServiceProvider provider = ServiceProvider.getInstance();
		//UserService userService = provider.getUserService();

		UserService userService = new UserServiceImpl();


		User user = null;
		RequestDispatcher requestDispatcher = null;
		try {user = userService.authorization(login, password);

			if (user == null) {
				response.sendRedirect("Controller?command=gotoindexpage&message=wrong2");
				return;
			}

			HttpSession session = request.getSession(true);
			session.setAttribute("auth", true);
			response.sendRedirect("Controller?command=gotomainpage");

		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=gotoindexpage&message=wrong in catch");
		}

	}

}
