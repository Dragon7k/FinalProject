package by.epam.gamestore.controller.command;

public enum CommandType {
    INVALID,
    HOME_PAGE,
    LOG_IN,
    LOG_OUT,
    SIGN_UP,
    VIEW_USER_COMMAND;

    public static CommandType getCommandType(String command) {
        if (command == null) {
            return INVALID;
        }
        CommandType type;
        try {
            type = CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            type = INVALID;
        }
        return type;
    }
}
