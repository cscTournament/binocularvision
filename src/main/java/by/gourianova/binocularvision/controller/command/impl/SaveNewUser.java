package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.UserService;
import by.gourianova.binocularvision.service.impl.UserServiceImpl;
import by.gourianova.binocularvision.util.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


public class SaveNewUser implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String password=request.getParameter("password");

		//TODO: decide were should be this code
		password= MD5.md5Encode(password);
		LocalDate dateTime=LocalDate.now();
		RegistrationInfo regInfo = new RegistrationInfo(request.getParameter("first_name"),request.getParameter("last_name"),request.getParameter("login"),password,request.getParameter("balance"),dateTime);
		UserService userService = new UserServiceImpl();
		userService.registration(regInfo);
		log.println("Registration SaveNewUser ok");

		request.setAttribute("message", "Registration OK");
		log.println	("class SaveNewUser implements Command "+request.getAttribute("message"));
		response.sendRedirect("Controller?command=gotoindexpage&message="+request.getAttribute("message"));
	}

}
