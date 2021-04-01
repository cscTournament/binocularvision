package by.gourianova.binocularvision.controller.command;

import by.gourianova.binocularvision.controller.command.impl.*;
import by.gourianova.binocularvision.controller.command.impl.admin.*;
import by.gourianova.binocularvision.controller.command.impl.app.GetAppData;
import by.gourianova.binocularvision.controller.command.impl.app.GetTypeData;
import by.gourianova.binocularvision.controller.command.impl.app.ShowAllTypes;
import by.gourianova.binocularvision.controller.command.impl.httpaddresses.AddHttpAddress;
import by.gourianova.binocularvision.controller.command.impl.httpaddresses.ShowAllHttpAddresses;
import by.gourianova.binocularvision.controller.command.impl.order.ShowAllOrders;
import by.gourianova.binocularvision.controller.command.impl.order.ShowUnclosedOrders;
import by.gourianova.binocularvision.controller.command.impl.order.ShowUserOrders;
import by.gourianova.binocularvision.controller.command.impl.role.AddRole;

import java.util.HashMap;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new Authorization());
		commands.put(CommandName.REGISTRATION,  new GoToRegistrationPage());
		commands.put(CommandName.SAVENEWUSER, new SaveNewUser());
		commands.put(CommandName.GOTOINDEXPAGE, new GoToIndexPage());
		commands.put(CommandName.GOTOMAINPAGE, new GoToMainPage());
		commands.put(CommandName.GOTOLOGINPAGE, new GoToLoginPage());
		commands.put(CommandName.GOTOADMINPAGE, new GoToAdminPage());
		commands.put(CommandName.CHANGEUSERROLE, new ChangeUserRole());
		commands.put(CommandName.GOTOAPPSPAGE , new GoToAppsPage());
		commands.put(CommandName.SHOWALLUSERS, new ShowAllUsers());
		commands.put(CommandName.FINDUSER, new FindUser());
		commands.put(CommandName.GOTOUSERCHANGEROLEPAGE, new GoToUserChangeRolePage());
		commands.put(CommandName.GOTOUSERSHOWONEPAGE, new  GoToUserShowOnePage());
		commands.put(CommandName.GOTOADDROLEPAGE,new GotoAddRolePage());
		commands.put(CommandName.ADDROLE, new AddRole());
        commands.put(CommandName.CHANGEUSERDATA, new ChangeUserData());
		commands.put(CommandName.GOTODELETEUSERPAGE, new GoToDeleteUserPage());
		commands.put(CommandName.SHOWALLROLES, new ShowAllRoles());
		commands.put(CommandName.DELETEUSER, new  DeleteUser());
		//TODO:write method
		commands.put(CommandName.SHOWALLORDERS, new ShowAllOrders());
		//TODO:write method
		commands.put(CommandName.SHOWUNCLOSEDORDERS, new ShowUnclosedOrders());
		//TODO:write method
		commands.put(CommandName.GETTYPEDATA, new GetTypeData());
		//TODO:write method
		commands.put(CommandName.SHOWALLTYPES, new ShowAllTypes());
		//TODO:write method
		commands.put(CommandName.GETAPPDATA, new GetAppData());

		//TODO:write method
		commands.put(CommandName.SHOWALLHTTPADDRESSES, new ShowAllHttpAddresses());
		//TODO:write method
		commands.put(CommandName.ADDHTTPADDRESS, new AddHttpAddress());

		commands.put(CommandName.ADDAPP, new AddApp());

		commands.put(CommandName.SHOWUSERORDERS, new ShowUserOrders());

		commands.put(CommandName.GOTOUSERCHANGEROLEPAGE, new GoToUserChangeRolePage());

		commands.put(CommandName.LOGOUT, new Logout());
	}
	
	
	public Command takeCommand(String name) {
	CommandName commandName;
		
		commandName = CommandName.valueOf(name.toUpperCase());
		log.println(commandName +" commandName");
		return commands.get(commandName);
	}

}
