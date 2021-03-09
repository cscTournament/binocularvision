package by.gourianova.binocularvision.controller.command;

import by.gourianova.binocularvision.controller.command.impl.*;


import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(CommandName.LOGIN, new Login());
		commands.put(CommandName.REGISTRATION, new GoToRegistrationPage());
		commands.put(CommandName.SAVENEWUSER, new SaveNewUser());
		commands.put(CommandName.GOTOINDEXPAGE, new GoToIndexPage());
		commands.put(CommandName.GOTOMAINPAGE, new GoToMainPage());
		commands.put(CommandName.LOGOUT, new Logout());
	}
	
	
	public Command takeCommand(String name) {
	CommandName commandName;
		
		commandName = CommandName.valueOf(name.toUpperCase());
		
		return commands.get(commandName);
	}

}
