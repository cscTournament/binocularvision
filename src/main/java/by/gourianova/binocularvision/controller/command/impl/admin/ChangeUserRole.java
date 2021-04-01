package by.gourianova.binocularvision.controller.command.impl.admin;


import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService;
import by.gourianova.binocularvision.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gourianova.binocularvision.util.PageOfConstants.*;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class ChangeUserRole implements Command {

    private final static String ROLE_ID = "roleId";
    private final static String USER_ID = "userId";
    private final static String USER = "userOne";
    private final static String EMPTY_USER = "emptyUser";
    private final static String MESSAGE = "message";
    private UserService userService = new UserServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int userId = Integer.parseInt(request.getParameter(USER_ID));
            User user = null;
            try {
                 user = userService.findUserById(userId);
                if (user != null) {
                    request.setAttribute(USER, user);
                    log.println("user not null" + request.getParameter(ROLE_ID) + "role Id ");

                    user.setRoleId(Integer.parseInt(request.getParameter(ROLE_ID)));
                    log.println("user's role updated at bean:" + user.getRoleId() + "role Id ");
                    userService.updateUser(user);

                    request.setAttribute("message", "Role is changed (OK)");

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(CHANGE_USER_ROLE_PAGE2);
                    requestDispatcher.forward(request, response);

            } else {
            request.setAttribute(EMPTY_USER, Boolean.TRUE);
        }
            } catch (ServiceException e) {
                request.getSession().setAttribute(MESSAGE, e.getMessage());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
                e.printStackTrace();
           }
    }
}
