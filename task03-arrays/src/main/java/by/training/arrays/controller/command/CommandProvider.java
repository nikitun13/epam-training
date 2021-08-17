package by.training.arrays.controller.command;

import by.training.arrays.controller.command.impl.*;

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
    public static final String SORT_COMMAND = "sort";
    public static final String MATRIX_COMMAND = "matrix";

    private final Map<String, Command> repository = new HashMap<>();
    private final Command unknownCommand = new UnknownCommand();

    private CommandProvider() {
        repository.put(CHANGE_LANGUAGE_COMMAND, new ChangeLanguageCommand());
        repository.put(EXIT_COMMAND, new ExitCommand());
        repository.put(HELP_COMMAND, new HelpCommand());
        repository.put(SORT_COMMAND, new SortCommand());
        repository.put(MATRIX_COMMAND, new MatrixCommand());
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
