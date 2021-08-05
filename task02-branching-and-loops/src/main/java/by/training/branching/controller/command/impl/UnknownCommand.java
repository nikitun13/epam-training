package by.training.branching.controller.command.impl;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code UnknownCommand} is a class that implements {@link Command}.
 * Executed when an unknown command is received.
 *
 * @author Nikita Romanov
 */
public class UnknownCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UnknownCommand.class);

    private CommandResult unknownCommandResult;

    @Override
    public CommandResult execute(String paramsLine) {
        if (unknownCommandResult == null) {
            unknownCommandResult = new CommandResult(CommandStatus.ERROR, TextManager.getText("error.unknownCommand"));
        } else {
            unknownCommandResult.setMessage(TextManager.getText("error.unknownCommand"));
        }
        logger.error("unknown command");
        return unknownCommandResult;
    }
}
