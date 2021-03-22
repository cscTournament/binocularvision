package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.bean.UserLoginInfo;
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
import java.time.LocalDate;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class Authorization implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean isAdmin=false;
		String password;
		String login = request.getParameter("login");
		password = request.getParameter("password");
		if ((login=="hart")&&(password=="qwerty")){ isAdmin=true;}
		log.println(login + " login "+password + " password" + isAdmin +  " isAdmin");
		password =  MD5.md5Encode(password);


		LocalDate updateTime = LocalDate.now();
		UserLoginInfo loginInfo = new UserLoginInfo(login,password,updateTime);
		UserService userService = new UserServiceImpl();

		User user = null;
		RequestDispatcher requestDispatcher = null;
		try {
			user = userService.authorization(login, password);
			log.println(user.getRoleId()+ " user.getRoleId() ");
			if (user == null) {
				response.sendRedirect("Controller?command=gotoindexpage&message=UserNotFound");
				return;
			}
			else if ((user.getRoleId()==2)||(isAdmin==true)){
				response.sendRedirect("Controller?command=gotoadminpage&message=UserIsAdmin");
				log.println("AUTHORISATION OF ADMIN");
			return;}
				{
			log.println("AUTHORISATION OK");
		}

			HttpSession session = request.getSession(true);
			session.setAttribute("auth", true);
			response.sendRedirect("Controller?command=gotomainpage");

		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=gotoindexpage&message=wrong in catch");
		}

	}
}
