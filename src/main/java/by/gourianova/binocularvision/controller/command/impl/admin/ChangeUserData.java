package by.gourianova.binocularvision.controller.command.impl.admin;

import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.bean.UserChangeInfo;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService;
import by.gourianova.binocularvision.service.impl.UserServiceImpl;
import by.gourianova.binocularvision.util.MD5;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.gourianova.binocularvision.util.PageOfConstants.*;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class ChangeUserData implements Command {
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String MESSAGE = "message";
    private final static String USER_ID = "userId";
    //  private UserService userService = new UserServiceImpl();
    User user = new User();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String password;
        log.println(request.getAttribute(USER_ID) + " id");
        int id = 0;
        if (request.getParameter(USER_ID) != null) {
            id = Integer.parseInt(request.getParameter(USER_ID));
        }

        String login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);
        password = MD5.md5Encode(password);
        UserChangeInfo loginInfo = new UserChangeInfo(id, login, password);
        UserService userService = new UserServiceImpl();
        User user = null;
        try {
            if (userService.updateUserData(loginInfo)) {
                log.println("login&password have changed OK");
                request.setAttribute(MESSAGE, "OK, login&password have changed ");
            } else {
                log.println("login&password have NOT changed");
                request.setAttribute(MESSAGE, "Couldn't changed login&password: they are not unique");
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ONE_USER);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            e.printStackTrace();
        }
    }
}
