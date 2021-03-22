package by.gourianova.binocularvision.controller.action;


public enum ActionType {

    CHANGELOCALE (new ChangeLocaleAction());


    Action action;

    Action getAction(){
        return action;
    }

    ActionType(ChangeLocaleAction action){
        this.action = action;
    }

}
