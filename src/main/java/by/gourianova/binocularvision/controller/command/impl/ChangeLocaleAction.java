package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.controller.Action;
import by.gourianova.binocularvision.controller.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
//@WebServlet("/ChangeLocaleAction")
public class ChangeLocaleAction implements Action {

    private final static String LOCALE = "locale";
    private final static String REFERRER = "referer";

    @Override
    public  Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String locale = LocaleType.getLocale(request.getParameter(LOCALE));
        session.setAttribute(LOCALE, locale);
        router.setPagePath((String) request.getSession().getAttribute(REFERRER));
        router.setRoute(Router.RouteType.REDIRECT);
        String referer = request.getHeader("referer");
        log.println(referer + " referer");
        router.setPagePath(referer);
        router.setRoute(Router.RouteType.REDIRECT);

        return router;
    }

}