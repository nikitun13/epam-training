package by.training.classes.controller.command.impl;

import by.training.classes.controller.command.Command;
import by.training.classes.controller.command.result.CommandResult;
import by.training.classes.controller.command.result.CommandStatus;
import by.training.classes.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code ExitCommand} is a class
 * that implements {@link Command}.<br/>
 * Executed when the command 'exit' is received.
 * This command terminates the application.
 *
 * @author Nikita Romanov
 */
public class ExitCommand implements Command {

    private static final Logger logger =
            LogManager.getLogger(ExitCommand.class);

    private final CommandResult exitResult =
            new CommandResult(CommandStatus.EXIT,
                    TextManager.getText("exit.shutdown")
            );

    @Override
    public CommandResult execute(String[] params) {
        logger.debug("Exit command is running");
        logger.debug("result: {}", exitResult);
        return exitResult;
    }
}
