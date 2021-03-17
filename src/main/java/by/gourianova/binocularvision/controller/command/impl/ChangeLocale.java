package by.gourianova.binocularvision.controller.command.impl;

import by.gourianova.binocularvision.controller.Action;
import by.gourianova.binocularvision.controller.Router;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class ChangeLocale implements Action {
        private final static String LOCALE = "locale";
        private final static String REFERRER = "referrer";

        @Override
        public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Router router = new Router();
            HttpSession session = request.getSession();
            String locale = LocaleType.getLocale(request.getParameter(LOCALE));
            session.setAttribute(LOCALE, locale);
            Cookie c = new Cookie(LOCALE, locale);
          //  c.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(c);

            //TODO:
      /*  router.setPagePath((String) request.getSession().getAttribute(REFERRER));
        router.setRoute(Router.RouteType.REDIRECT);*/


            //TODO подумать что делать с этим методом - пока тут костыль... когда идем форвардом мы не можем получить реферер страницы...
            String referer = request.getHeader("referer");

          //TODO: rewrite
         //  String

            log.println(request.getContextPath()+"referer !!!!!!!!!!!!!!!!!!!!!!!!");
            if (referer.equals("http://localhost:7788/Controller")) {
                router.setPagePath((String) request.getSession().getAttribute(REFERRER));
                // router.setPagePath(PageConstant.FIRST_PAGE);
                router.setRoute(Router.RouteType.REDIRECT);
            } else {
                router.setPagePath(referer);
                router.setRoute(Router.RouteType.REDIRECT);
            }
            return router;
        }


}
