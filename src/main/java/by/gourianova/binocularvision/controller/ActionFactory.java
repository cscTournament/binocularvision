package by.gourianova.binocularvision.controller;

import javax.servlet.http.HttpServletRequest;


public class ActionFactory {

    public static Action getAction(HttpServletRequest request){
        ActionType entry = ActionType.valueOf(request.getParameter("command").toUpperCase());
        return entry.getAction();
    }
}
