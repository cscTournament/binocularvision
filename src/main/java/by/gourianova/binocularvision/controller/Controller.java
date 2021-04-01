package by.gourianova.binocularvision.controller;


import by.gourianova.binocularvision.controller.action.Action;
import by.gourianova.binocularvision.controller.action.ActionFactory;
import by.gourianova.binocularvision.controller.action.ActionType;
import by.gourianova.binocularvision.controller.action.Router;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.controller.command.CommandName;
import by.gourianova.binocularvision.controller.command.CommandProvider;
import by.gourianova.binocularvision.util.PageOfConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    boolean isAction = false;

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



    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = null;

        Router router;
        HttpSession session = request.getSession();
        String name = request.getParameter("command");
        log.println(name + " name");
           boolean isAction = true;
            try {
                ActionType.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException e) {
                log.println("not  change local name:" + name.toUpperCase());
                isAction = false;
            }
            if (isAction) {

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
                    response.sendRedirect(PageOfConstants.ERROR_PAGE);
                }
            }
    else {
        boolean isCommand = false;
        try {
            CommandName.valueOf(name.toUpperCase());
            isCommand = true;
        }catch (Exception e){throw new IllegalArgumentException ("not command maybe change local " + name.toUpperCase());}

        if (isCommand) {
            command = provider.takeCommand(name);
            try {
                command.execute(request, response);
            } catch (Exception e) {
                //throw new IllegalArgumentException("Smth wrong with Controller process try it later");
                response.sendRedirect(PageOfConstants.ERROR_PAGE);
                e.printStackTrace();
            }
           }
        }
 }
}


