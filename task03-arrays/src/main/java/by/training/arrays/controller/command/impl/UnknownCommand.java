package by.training.arrays.controller.command.impl;

import by.training.arrays.controller.command.Command;
import by.training.arrays.controller.command.result.CommandResult;
import by.training.arrays.controller.command.result.CommandStatus;
import by.training.arrays.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code UnknownCommand} is a class
 * that implements {@link Command}.<br/>
 * Executed when an unknown command is received.
 *
 * @author Nikita Romanov
 */
public class UnknownCommand implements Command {

    private static final Logger logger =
            LogManager.getLogger(UnknownCommand.class);

    private final CommandResult unknownCommandResult =
            new CommandResult(
                    CommandStatus.ERROR,
                    TextManager.getText("error.unknownCommand")
            );

    @Override
    public CommandResult execute(String[] params) {
        unknownCommandResult.setMessage(
                TextManager.getText("error.unknownCommand")
        );
        logger.error("unknown command");
        logger.debug("result: {}", unknownCommandResult);
        return unknownCommandResult;
    }
}
