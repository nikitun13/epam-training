package by.training.arrays.controller.command.impl;

import by.training.arrays.controller.command.Command;
import by.training.arrays.controller.command.result.CommandResult;
import by.training.arrays.controller.command.result.CommandStatus;
import by.training.arrays.view.manager.Language;
import by.training.arrays.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * The class {@code ChangeLanguageCommand} is a class
 * that implements {@link Command}.<br/>
 * Executed when the command 'chlang' is received.
 * This command changes language.
 *
 * @author Nikita Romanov
 */
public class ChangeLanguageCommand implements Command {

    private static final Logger logger =
            LogManager.getLogger(ChangeLanguageCommand.class);

    @Override
    public CommandResult execute(String[] params) {
        String arrayStatus = Arrays.toString(params);
        logger.debug("received params: {}", arrayStatus);
        CommandResult result;
        if (params.length == 1) {
            String langKey = params[0].toUpperCase();
            result = changeLanguage(langKey);
        } else {
            logger.error("Invalid number of parameters");
            result = new CommandResult(
                    CommandStatus.INVALID_NUMBER_OF_PARAMETERS
            );
        }
        logger.debug("result: {}", result);
        return result;
    }

    private CommandResult changeLanguage(String langKey) {
        logger.debug("received langKey: {}", langKey);
        CommandResult result;
        if (Language.isContains(langKey)) {
            TextManager.setLanguage(Language.valueOf(langKey));
            result = new CommandResult(
                    CommandStatus.OK, TextManager.getText("chlang.result")
            );
        } else {
            result = new CommandResult(CommandStatus.INVALID_INPUT_PARAMETERS);
            logger.error("Invalid language parameter: {}", langKey);
        }
        return result;
    }
}
