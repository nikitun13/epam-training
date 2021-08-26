package by.training.classes.controller.command;

import by.training.classes.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The class {@code CommandProvider} is utility class
 * that provides the implementation of {@link Command}.
 *
 * @author Nikita Romanov
 * @see Command
 */
public final class CommandProvider {

    private static final CommandProvider INSTANCE = new CommandProvider();

    public static final String CHANGE_LANGUAGE_COMMAND = "chlang";
    public static final String EXIT_COMMAND = "exit";
    public static final String HELP_COMMAND = "help";
    public static final String TEXT_COMMAND = "text";

    private final Map<String, Command> repository = new HashMap<>();
    private final Command unknownCommand = new UnknownCommand();

    private CommandProvider() {
        repository.put(CHANGE_LANGUAGE_COMMAND, new ChangeLanguageCommand());
        repository.put(EXIT_COMMAND, new ExitCommand());
        repository.put(HELP_COMMAND, new HelpCommand());
        repository.put(TEXT_COMMAND, new TextCommand());
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the command corresponding to its name.<br/>
     * If there is no command with the such a name,
     * it returns {@link UnknownCommand}.
     *
     * @param commandName name of the command.
     * @return the command corresponding to its name.
     * @see UnknownCommand
     */
    public Command getCommand(String commandName) {
        return repository.getOrDefault(commandName, unknownCommand);
    }
}
