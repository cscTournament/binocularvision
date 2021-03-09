package by.gourianova.binocularvision.controller;

import by.gourianova.binocularvision.controller.command.*;
import by.gourianova.binocularvision.controller.action.*;
import by.gourianova.binocularvision.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet(name = "controller", urlPatterns = "/controller")

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final CommandProvider provider = new CommandProvider(); 

	public Controller() {
		super();
	}
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String name;
		Command command;
		
		name = request.getParameter("command");
		command = provider.takeCommand(name);
		
		command.execute(request, response);
	}
	//TODO: fix
/*	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Router router;
		HttpSession session = request.getSession();
		Action action = ActionFactory.getAction(request);
		if (action != null) {
			router = action.execute(request, response);
			switch (router.getRoute()) {
				case REDIRECT:
					response.sendRedirect(router.getPagePath());
					break;
				case FORWARD:
					request.getRequestDispatcher(router.getPagePath()).forward(request, response);
					break;
			}
		} else {
			session.setAttribute("message", "Wrong command.");
			response.sendRedirect(PageConstant.ERROR_PAGE);
		}
	}*/

}
