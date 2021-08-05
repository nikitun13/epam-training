package by.training.branching.controller.command.impl;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.view.manager.Language;
import by.training.branching.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * The class {@code ChangeLanguageCommand} is a class that implements {@link Command}.
 * Executed when the command 'chlang' is received.
 * This command changes language.
 *
 * @author Nikita Romanov
 */
public class ChangeLanguageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeLanguageCommand.class);

    @Override
    public CommandResult execute(String paramsLine) {
        logger.debug("received params: {}", paramsLine);
        String[] params = paramsLine.split(Command.DELIMITER);
        String arrayStatus = Arrays.toString(params);
        logger.debug("split params: {}", arrayStatus);
        CommandResult result;
        if (params.length == 1) {
            String langKey = params[0].toUpperCase();
            List<String> list = Arrays.stream(Language.values())
                    .map(Language::toString)
                    .toList();
            if (list.contains(langKey)) {
                TextManager.setLanguage(Language.valueOf(langKey));
                result = new CommandResult(CommandStatus.OK, TextManager.getText("chlang.changed"));
            } else {
                result = new CommandResult(CommandStatus.ERROR, TextManager.getText("error.invalidParameters"));
                logger.error("Invalid language parameter: {}", langKey);
            }
        } else {
            result = new CommandResult(CommandStatus.ERROR, TextManager.getText("error.invalidNumberOfParameters"));
            logger.error("Invalid number of parameters");
        }
        logger.debug("result: {}", result);
        return result;
    }
}
