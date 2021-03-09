package by.gourianova.binocularvision.controller.action;

import javax.servlet.http.HttpServletRequest;


public class ActionFactory {

    public static Action getAction(HttpServletRequest request){
        ActionType entry = ActionType.valueOf(request.getParameter("action").toUpperCase());
        //TODO:fix
        return null;//entry.getAction();
    }
}
