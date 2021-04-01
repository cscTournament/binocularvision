package by.gourianova.binocularvision.controller.command.impl.order;


import by.gourianova.binocularvision.bean.Order;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.OrderService;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.impl.OrderServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static by.gourianova.binocularvision.util.PageOfConstants.ERROR_PAGE;


public class ShowUserOrders implements Command {
    private final static String USER_ID = "userId";
    private final static String ORDERS_LIST = "ordersList";
    private final static String EMPTY_ORDER = "emptyOrder";
    private final static String MESSAGE = "message";
    private OrderService orderService = new OrderServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*int userId = (int) request.getAttribute(USER_ID);*/
        ArrayList<Order> ordersList = null;
        try {
            ordersList = orderService.findAllUserOrders(Integer.parseInt(request.getParameter(USER_ID)));
            if (!ordersList.isEmpty()) {
                request.setAttribute(ORDERS_LIST, ordersList);
            } else {
                request.setAttribute(EMPTY_ORDER, Boolean.TRUE);
            }
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
            e.printStackTrace();
        }


    }
}
