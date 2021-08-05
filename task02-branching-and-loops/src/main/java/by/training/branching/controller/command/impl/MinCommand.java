package by.training.branching.controller.command.impl;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.service.IntegerComparatorService;
import by.training.branching.service.factory.ServiceFactory;
import by.training.branching.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * The class {@code MinCommand} is a class that implements {@link Command}.
 * Calls the tasks of {@link IntegerComparatorService}.
 *
 * @author Nikita Romanov
 * @see IntegerComparatorService
 */
public class MinCommand implements Command {

    private static final Logger logger = LogManager.getLogger(MinCommand.class);

    private static final String INT_REGEX = "[-+]?\\d+";

    private final IntegerComparatorService service =
            ServiceFactory.getInstance().getIntegerComparatorService();

    @Override
    public CommandResult execute(String paramsLine) {
        logger.debug("received params: {}", paramsLine);
        CommandResult result;
        String[] params = splitParams(paramsLine);
        String arrayStatus = Arrays.toString(params);
        logger.debug("split params: {}", arrayStatus);
        if (isValidNumberOfParams(params)) {
            if (isValidParams(params)) {
                int a = Integer.parseInt(params[0]);
                int b = Integer.parseInt(params[1]);
                int min = service.min(a, b);
                result = new CommandResult(
                        CommandStatus.OK,
                        TextManager.getText("min.result") + DELIMITER + min
                );
            } else {
                result = new CommandResult(
                        CommandStatus.ERROR,
                        TextManager.getText("error.invalidParameters")
                );
                logger.error("Invalid parameters: {}", arrayStatus);
            }
        } else {
            result = new CommandResult(
                    CommandStatus.ERROR,
                    TextManager.getText("error.invalidNumberOfParameters")
            );
            logger.error("Invalid number of parameters");
        }
        logger.debug("result: {}", result);
        return result;
    }

    private boolean isValidNumberOfParams(String[] params) {
        return params.length == 2;
    }

    private boolean isValidParams(String[] params) {
        for (String param : params) {
            if (!isInteger(param)) {
                return false;
            }
        }
        return true;
    }

    private boolean isInteger(String param) {
        return param.matches(INT_REGEX);
    }
}
