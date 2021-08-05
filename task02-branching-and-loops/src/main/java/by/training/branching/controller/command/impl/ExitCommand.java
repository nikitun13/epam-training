package by.training.branching.controller.command.impl;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code ExitCommand} is a class that implements {@link Command}.
 * Executed when the command 'exit' is received.
 * This command terminates the application.
 *
 * @author Nikita Romanov
 */
public class ExitCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ExitCommand.class);

    private CommandResult exitResult;

    @Override
    public CommandResult execute(String paramsLine) {
        logger.debug("Exit command is running");
        if (exitResult == null) {
            exitResult = new CommandResult(CommandStatus.EXIT, TextManager.getText("exit.shutdown"));
        } else {
            exitResult.setMessage(TextManager.getText("exit.shutdown"));
        }
        logger.debug("result: {}", exitResult);
        return exitResult;
    }
}
