package by.training.branching.controller.command.impl;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.service.FunctionCalculatorService;
import by.training.branching.service.ServiceException;
import by.training.branching.service.factory.ServiceFactory;
import by.training.branching.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;
import java.util.Arrays;

/**
 * The class {@code FuncCommand} is a class that implements {@link Command}.
 * Calls the tasks of {@link FunctionCalculatorService}.
 *
 * @author Nikita Romanov
 * @see FunctionCalculatorService
 */
public class FuncCommand implements Command {

    private static final Logger logger = LogManager.getLogger(FuncCommand.class);

    private static final String DOUBLE_REGEX = "[+-]?\\d+(\\.\\d+)?";

    private final FunctionCalculatorService service =
            ServiceFactory.getInstance().getFunctionCalculatorService();

    @Override
    public CommandResult execute(String paramsLine) {
        logger.debug("received params: {}", paramsLine);
        CommandResult result;
        String[] params = splitParams(paramsLine);
        String arrayStatus = Arrays.toString(params);
        logger.debug("split params: {}", arrayStatus);
        if (!paramsLine.isBlank() && isValidNumberOfParams(params)) {
            result = calcFunc(params);
        } else {
            logger.error("Invalid number of parameters");
            result = new CommandResult(
                    CommandStatus.ERROR,
                    TextManager.getText("error.invalidNumberOfParameters")
            );
        }
        logger.debug("result: {}", result);
        return result;
    }

    private CommandResult calcFunc(String[] params) {
        CommandResult result;
        if (isValidParams(params)) {
            double x = Double.parseDouble(params[0]);
            try {
                double serviceResponse = service.calculate(x);
                NumberFormat numberFormat = TextManager.getCurrentNumberFormat();
                result = new CommandResult(
                        CommandStatus.OK,
                        TextManager.getText("func.result") +
                                DELIMITER +
                                numberFormat.format(serviceResponse)
                );
            } catch (ServiceException e) {
                logger.error(e);
                result = new CommandResult(
                        CommandStatus.ERROR,
                        TextManager.getText("func.error.xIsOutOfRange")
                );
            }
        } else {
            result = new CommandResult(
                    CommandStatus.ERROR,
                    TextManager.getText("error.invalidParameters")
            );
            String arrayStatus = Arrays.toString(params);
            logger.error("Invalid parameters: {}", arrayStatus);
        }
        return result;
    }

    private boolean isValidNumberOfParams(String[] params) {
        return params.length == 1;
    }

    private boolean isValidParams(String[] params) {
        for (String param : params) {
            if (!isDouble(param)) {
                return false;
            }
        }
        return true;
    }

    private boolean isDouble(String param) {
        return param.matches(DOUBLE_REGEX);
    }
}
