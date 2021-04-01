package by.gourianova.binocularvision.controller.command.impl.admin;

import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService;
import by.gourianova.binocularvision.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.gourianova.binocularvision.util.PageOfConstants.*;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class DeleteUser implements Command {
    private final static String USER_ID = "userId";
    private final static String MESSAGE = "message";
    private final UserService userService = new UserServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId = Integer.parseInt(request.getParameter(USER_ID));
        boolean isDeleted = false;
        try {
            isDeleted = userService.deleteUserById(userId);
            if (isDeleted) {
                request.setAttribute("message", "delete.user.ok");
                log.println("user with " + userId + " is deleted ");

            } else {
                request.setAttribute("message", "delete.user.not");
                log.println("user with " + userId + " is Not deleted ");
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(DELETE_USER_PAGE2);
            requestDispatcher.forward(request, response);

        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            e.printStackTrace();
        }
    }
}

