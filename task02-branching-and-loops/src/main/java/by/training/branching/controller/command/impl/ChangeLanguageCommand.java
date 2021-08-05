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
        CommandResult result;
        logger.debug("received params: {}", paramsLine);
        String[] params = splitParams(paramsLine);
        String arrayStatus = Arrays.toString(params);
        logger.debug("split params: {}", arrayStatus);
        if (isValidNumberOfParams(params)) {
            String langKey = params[0].toUpperCase();
            if (isValidParamLang(langKey)) {
                TextManager.setLanguage(Language.valueOf(langKey));
                result = new CommandResult(CommandStatus.OK, TextManager.getText("chlang.result"));
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

    private boolean isValidNumberOfParams(String[] params) {
        return params.length == 1;
    }

    private boolean isValidParamLang(String langKey) {
        List<String> list = Arrays.stream(Language.values())
                .map(Language::toString)
                .toList();
        return list.contains(langKey);
    }
}
