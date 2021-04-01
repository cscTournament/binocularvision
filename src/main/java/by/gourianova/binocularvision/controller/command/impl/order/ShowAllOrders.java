package by.gourianova.binocularvision.controller.command.impl.order;

import by.gourianova.binocularvision.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllOrders implements Command {
    private final static String ORDERS_LIST = "ordersList";
    private final static String EMPTY_ORDER = "emptyOrder";
    private final static String MESSAGE = "message";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
   /* private OrderService orderService = new OrderServiceImpl();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            ArrayList<Order> ordersList = orderService.findAll();
            if (!ordersList.isEmpty()) {
                request.setAttribute(ORDERS_LIST, ordersList);
            } else {
                request.setAttribute(EMPTY_ORDER, Boolean.TRUE);
            }
            router.setPagePath(PageConstant.ALL_ORDERS);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }

    }*/
}
