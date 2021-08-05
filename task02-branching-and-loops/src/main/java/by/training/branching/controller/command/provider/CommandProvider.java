package by.training.branching.controller.command.provider;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The class {@code CommandProvider} is utility class that provides the implementation of {@link Command}.
 *
 * @author Nikita Romanov
 * @see Command
 */
public final class CommandProvider {

    private static final CommandProvider INSTANCE = new CommandProvider();

    public static final String CHANGE_LANGUAGE_COMMAND = "chlang";
    public static final String EXIT_COMMAND = "exit";
    public static final String HELP_COMMAND = "help";
    public static final String MIN_COMMAND = "min";
    public static final String REPLACE_COMMAND = "replace";
    public static final String DIVISOR_COMMAND = "divisor";
    public static final String FUNC_COMMAND = "func";
    public static final String ODD_SUM_COMMAND = "oddsum";
    public static final String SERIES_COMMAND = "series";
    public static final String CONVERT_COMMAND = "convert";

    private final Map<String, Command> repository = new HashMap<>();
    private final Command unknownCommand = new UnknownCommand();

    private CommandProvider() {
        repository.put(CHANGE_LANGUAGE_COMMAND, new ChangeLanguageCommand());
        repository.put(EXIT_COMMAND, new ExitCommand());
        repository.put(HELP_COMMAND, new HelpCommand());
        repository.put(MIN_COMMAND, new MinCommand());
        repository.put(REPLACE_COMMAND, new ReplaceCommand());
        repository.put(DIVISOR_COMMAND, new DivisorCommand());
        repository.put(FUNC_COMMAND, new FuncCommand());
        repository.put(ODD_SUM_COMMAND, new OddSumCommand());
        repository.put(SERIES_COMMAND, new SeriesCommand());
        repository.put(CONVERT_COMMAND, new ConvertCommand());
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the command corresponding to its name.
     * If there is no command with the such a name, it returns {@link UnknownCommand}.
     *
     * @param commandName name of the command.
     * @return the command corresponding to its name.
     * @see UnknownCommand
     */
    public Command getCommand(String commandName) {
        return repository.getOrDefault(commandName, unknownCommand);
    }
}
