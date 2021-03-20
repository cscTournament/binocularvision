package by.gourianova.binocularvision.controller;


import by.gourianova.binocularvision.controller.command.impl.ChangeLocaleAction;

public enum ActionType {
   /* LOGIN (new LoginUserAction()),
    LOGOUT (new LogoutUserAction()),
    REGISTER (new RegisterUserAction()),

    RENT_APP(new RentAppAction()),
    RETURN_APP(new ReturnAppsAction()),

    ADD_MONEY(new AddMoneyAction()),
    ADD_TYPE(new AddTypeAction()),
    ADD_HTTPADDRESS(new AddHttpAddressAction()),
    ADD_ROLE(new AddRoleAction()),
    ADD_APP(new AddAppAction()),
*/
   //AUTHORIZATION (new Authorization), REGISTRATION, SAVENEWUSER, GOTOINDEXPAGE, GOTOMAINPAGE, GOTOLOGINPAGE,
    //LOGOUT
    CHANGELOCALE (new ChangeLocaleAction());





  /*  CHANGE_USER(new ChangeUserAction()),
    CHANGE_TYPE(new ChangeTypeAction()),

    SHOW_ORDERS(new ShowOrdersAction()),
    SHOW_USER_ORDERS(new ShowUserOrdersAction()),
    SHOW_ALL_APPS (new ShowAllAppsAction()),
    SHOW_ALL_USERS (new ShowAllUserAction()),
    SHOW_ALL_TYPES (new ShowAllTypesAction()),
    SHOW_ALL_ROLES (new ShowAllRoleAction()),
    SHOW_ALL_ORDERS (new ShowAllOrdersAction()),
    SHOW_ALL_HTTPADDRESSES (new ShowAllHttpAddressesAction()),
    SHOW_UNCLOSED_ORDERS (new ShowUnclosedOrdersAction()),
    SHOW_ADMIN_PAGE(new ShowAdminPageAction()),
    SHOW_APPS_PAGE (new ShowAllAppsByPageAction()),

    FIND_USER(new FindUserAction()),
    GET_USER_DATA (new GetUserDataAction()),
    GET_APP_DATA (new GetAppDataAction()),
    GET_TYPE_DATA (new GetTypeDataAction());
*/
    Action action;

    Action getAction(){
        return action;
    }

    ActionType(ChangeLocaleAction action){
        this.action = action;
    }

}
