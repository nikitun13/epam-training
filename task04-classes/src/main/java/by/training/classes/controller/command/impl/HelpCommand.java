package by.training.classes.controller.command.impl;

import by.training.classes.controller.command.Command;
import by.training.classes.controller.command.result.CommandResult;
import by.training.classes.controller.command.result.CommandStatus;
import by.training.classes.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * The class {@code HelpCommand} is a class
 * that implements {@link Command}.<br/>
 * Executed when the command 'help' is received.
 * This command describes the functionality of other commands.
 *
 * @author Nikita Romanov
 */
public class HelpCommand implements Command {

    private static final Logger logger =
            LogManager.getLogger(HelpCommand.class);

    private static final String HELP_KEY = "help.";

    @Override
    public CommandResult execute(String[] params) {
        String arrayStatus = Arrays.toString(params);
        logger.debug("received params: {}", arrayStatus);
        CommandResult result = switch (params.length) {
            case 0 -> getAllCommands();
            case 1 -> getDescription(params[0]);
            default -> {
                logger.error("Invalid number of parameters");
                yield new CommandResult(
                        CommandStatus.INVALID_NUMBER_OF_PARAMETERS
                );
            }
        };
        logger.debug("result: {}", result);
        return result;
    }

    private CommandResult getAllCommands() {
        String commands =
                TextManager.getText("menu.allCommandsMessage") + "\n"
                        + TextManager.getText("menu.text") + "\n"
                        + TextManager.getText("menu.chlang") + "\n"
                        + TextManager.getText("menu.exit") + "\n\n"
                        + TextManager.getText("menu.help");
        return new CommandResult(CommandStatus.OK, commands);
    }

    private CommandResult getDescription(String commandName) {
        CommandResult result;
        String key = HELP_KEY + commandName;
        if (TextManager.isExist(key)) {
            result = new CommandResult(
                    CommandStatus.OK,
                    TextManager.getText(key)
            );
        } else {
            result = new CommandResult(
                    CommandStatus.INVALID_INPUT_PARAMETERS,
                    TextManager.getText("help.error.unknownCommand")
                            + commandName
            );
        }
        return result;
    }
}
