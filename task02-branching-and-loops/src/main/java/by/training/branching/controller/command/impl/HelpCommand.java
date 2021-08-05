package by.training.branching.controller.command.impl;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * The class {@code HelpCommand} is a class that implements {@link Command}.
 * Executed when the command 'help' is received.
 * This command describes the functionality of other commands.
 *
 * @author Nikita Romanov
 */
public class HelpCommand implements Command {

    private static final Logger logger = LogManager.getLogger(HelpCommand.class);

    @Override
    public CommandResult execute(String paramsLine) {
        logger.debug("received params: {}", paramsLine);
        CommandResult result;
        if (paramsLine.isBlank()) {
            result = getAllCommands();
        } else {
            result = getDescription(paramsLine);
        }
        logger.debug("result: {}", result);
        return result;
    }

    private CommandResult getAllCommands() {
        String commands = TextManager.getText("menu.allCommandsMessage") + "\n" +
                TextManager.getText("menu.min") + "\n" +
                TextManager.getText("menu.replace") + "\n" +
                TextManager.getText("menu.divisor") + "\n" +
                TextManager.getText("menu.func") + "\n" +
                TextManager.getText("menu.oddsum") + "\n" +
                TextManager.getText("menu.series") + "\n" +
                TextManager.getText("menu.convert") + "\n" +
                TextManager.getText("menu.chlang") + "\n" +
                TextManager.getText("menu.exit") + "\n\n" +
                TextManager.getText("menu.help");
        return new CommandResult(CommandStatus.OK, commands);
    }

    private CommandResult getDescription(String paramsLine) {
        CommandResult result;
        String[] params = splitParams(paramsLine);
        String arrayStatus = Arrays.toString(params);
        logger.debug("split params: {}", arrayStatus);
        if (isValidNumberOfParams(params)) {
            String commandName = params[0];
            String textKey = "help." + commandName;
            if (isValidParamCommandName(textKey)) {
                result = new CommandResult(
                        CommandStatus.OK,
                        TextManager.getText(textKey)
                );
            } else {
                result = new CommandResult(
                        CommandStatus.ERROR,
                        TextManager.getText("error.invalidParameters")
                );
                logger.error("Invalid parameter commandName: {}", commandName);
            }
        } else {
            result = new CommandResult(
                    CommandStatus.ERROR,
                    TextManager.getText("error.invalidNumberOfParameters")
            );
            logger.error("Invalid number of parameters");
        }
        return result;
    }

    private boolean isValidNumberOfParams(String[] params) {
        return params.length == 1;
    }

    private boolean isValidParamCommandName(String textKey) {
        return TextManager.isExist(textKey);
    }
}
