package by.gourianova.binocularvision.controller.command;

import by.gourianova.binocularvision.controller.command.impl.*;
import by.gourianova.binocularvision.controller.command.impl.admin.FindUser;
import by.gourianova.binocularvision.controller.command.impl.admin.ShowAllUsers;

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
		commands.put(CommandName.GOTOAPPSPAGE , new GoToAppsPage());
		commands.put(CommandName.SHOWALLUSERS, new ShowAllUsers());
		commands.put(CommandName.FINDUSER, new FindUser());
		commands.put(CommandName.LOGOUT, new Logout());
	}
	
	
	public Command takeCommand(String name) {
	CommandName commandName;
		
		commandName = CommandName.valueOf(name.toUpperCase());
		log.println(commandName +" commandName");
		return commands.get(commandName);
	}

}
