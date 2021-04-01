package by.gourianova.binocularvision.controller.action;


import by.gourianova.binocularvision.controller.action.local.ChangeLocaleAction;

public enum ActionType {
   // FINDUSERACTION (new FindUserAction()),
    CHANGELOCALE (new ChangeLocaleAction());


    Action action;

   // ActionType(FindUserAction action) {
   //     this.action = action;
    //}

    Action getAction(){
        return action;
    }

    ActionType(ChangeLocaleAction action){
        this.action = action;
    }

}
