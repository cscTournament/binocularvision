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

import static by.gourianova.binocularvision.util.PageOfConstants.ERROR_PAGE;
import static by.gourianova.binocularvision.util.PageOfConstants.FIRST_PAGE;


public class ChangeUser implements Command {
    private final static String USER_ID = "userId";
    private final static String ROLE_ID = "roleId";
    private final static String MESSAGE = "message";
    private UserService userService = new UserServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        try {
            user.setId(Integer.parseInt(request.getParameter(USER_ID)));
            user.setRoleId(Integer.parseInt(request.getParameter(ROLE_ID)));
            userService.updateUser(user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(FIRST_PAGE);
            requestDispatcher.forward(request, response);

        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            e.printStackTrace();
        }

    }
}
