package by.epam.gamestore.controller.command;


import by.epam.gamestore.controller.command.impl.HomePageCommand;
import by.epam.gamestore.controller.command.impl.LogInCommand;
import by.epam.gamestore.controller.command.impl.LogOutCommand;
import by.epam.gamestore.controller.command.impl.SignUpCommand;

import java.util.EnumMap;

import static by.epam.gamestore.controller.command.CommandType.*;

public final class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private EnumMap<CommandType, Command> commands;

    private CommandFactory() {
        commands = new EnumMap<>(CommandType.class);
        commands.put(HOME_PAGE, new HomePageCommand());
        commands.put(LOG_IN, new LogInCommand());
        commands.put(LOG_OUT, new LogOutCommand());
        commands.put(SIGN_UP, new SignUpCommand());
        /*commands.put(GO_TO_SIGN_UP_PAGE, new GoToSignUpPageCommand());
        commands.put(GO_TO_ABOUT_PAGE, new GoToAboutPageCommand());
        commands.put(GO_TO_RECIPES_PAGE, new GoToRecipesPageCommand());
        commands.put(VIEW_FULL_RECIPE, new ViewFullRecipeCommand());
        commands.put(GO_TO_EDIT_BLOGGER_INFO, new GoToEditBloggerInfoCommand());
        commands.put(GO_TO_ADD_NEW_POST, new GoToAddNewPostCommand());
        commands.put(GO_TO_PROFILE_PAGE, new GoToProfilePageCommand());
        commands.put(EDIT_BLOGGER_INFO, new EditBloggerInfoCommand());
        commands.put(ADD_RECIPE_POST, new AddRecipePostCommand());
        commands.put(COMMENT, new CommentCommand());
        commands.put(DELETE_POST, new DeleteRecipePostCommand());
        commands.put(CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(LOGOUT, new LogoutCommand());

        commands.put(SIGN_UP, new SignUpCommand());*/
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command getCommand(String command) {
        CommandType commandType = CommandType.getCommandType(command);
        return commands.get(commandType);
    }
}
