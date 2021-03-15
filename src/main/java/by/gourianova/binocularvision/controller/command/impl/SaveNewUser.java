package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.bean.RegistrationInfo;
import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.controller.command.Command;

import by.gourianova.binocularvision.service.UserService;
import by.gourianova.binocularvision.service.impl.UserServiceImpl;
import by.gourianova.binocularvision.util.MD5;
import lombok.extern.slf4j.Slf4j;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.time.LocalDate;


//import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

//@Slf4j
public class SaveNewUser implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String password=request.getParameter("password");
		password= MD5.md5Encode(password);
		LocalDate dateTime=LocalDate.now();

		String balance = "3.3";
		User user=new User(1,request.getParameter("email"),password, request.getParameter("name"),request.getParameter("surname"),new BigDecimal(balance),2,dateTime);

		RegistrationInfo regInfo = new RegistrationInfo(request.getParameter("name"),request.getParameter("surname"),request.getParameter("email"),password);

		//User user=new User(1,request.getParameter("login"),password, request.getParameter("first_name"),request.getParameter("last_name"),new BigDecimal(balance),2,dateTime);

		//RegistrationInfo regInfo = new RegistrationInfo(request.getParameter("first_name"),request.getParameter("last_name"),request.getParameter("login"),password);

		UserService userService = new UserServiceImpl();
		userService.registration(regInfo);

			request.setAttribute("message", "Registration OK");



		//log.print();
		System.out.println	("class SaveNewUser implements Command "+request.getAttribute("message") +" with password: " + password);

		request.setAttribute("message", "Registration OK");


		
		response.sendRedirect("Controller?command=gotoindexpage&message="+request.getAttribute("message"));



		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		//requestDispatcher.forward(request, response);
		
	}

}
