package by.training.branching.controller.command.impl;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.service.RealNumberTransformerService;
import by.training.branching.service.ServiceException;
import by.training.branching.service.factory.ServiceFactory;
import by.training.branching.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

/**
 * The class {@code ReplaceCommand} is a class that implements {@link Command}.
 * Calls the tasks of {@link RealNumberTransformerService}.
 *
 * @author Nikita Romanov
 * @see RealNumberTransformerService
 */
public class ReplaceCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ReplaceCommand.class);

    private static final String DOUBLE_REGEX = "[+-]?\\d+(\\.\\d+)?";

    private final RealNumberTransformerService service =
            ServiceFactory.getInstance().getRealNumberTransformerService();

    @Override
    public CommandResult execute(String paramsLine) {
        logger.debug("received params: {}", paramsLine);
        CommandResult result;
        String[] params = splitParams(paramsLine);
        String arrayStatus = Arrays.toString(params);
        logger.debug("split params: {}", arrayStatus);
        result = switch (params.length) {
            case 2 -> transformTwoDifferentRealNumbers(params);
            case 3 -> transformThreeRealNumbers(params);
            default -> {
                logger.error("Invalid number of parameters");
                yield new CommandResult(
                        CommandStatus.ERROR,
                        TextManager.getText("error.invalidNumberOfParameters")
                );
            }
        };
        logger.debug("result: {}", result);
        return result;
    }

    private CommandResult transformTwoDifferentRealNumbers(String[] params) {
        CommandResult result;
        if (isValidParams(params)) {
            double a = Double.parseDouble(params[0]);
            double b = Double.parseDouble(params[1]);
            try {
                List<Double> serviceResponse = service.transformTwoDifferentRealNumbers(a, b);
                NumberFormat numberFormat = TextManager.getCurrentNumberFormat();
                String firstNumber = numberFormat.format(serviceResponse.get(0));
                String secondNumber = numberFormat.format(serviceResponse.get(1));
                result = new CommandResult(
                        CommandStatus.OK,
                        TextManager.getText("result") +
                                DELIMITER +
                                "a = " + firstNumber +
                                DELIMITER +
                                "b = " + secondNumber
                );
            } catch (ServiceException e) {
                logger.error(e);
                result = new CommandResult(
                        CommandStatus.ERROR,
                        TextManager.getText("replace.error.theSameValue")
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

    private CommandResult transformThreeRealNumbers(String[] params) {
        CommandResult result;
        if (isValidParams(params)) {
            double a = Double.parseDouble(params[0]);
            double b = Double.parseDouble(params[1]);
            double c = Double.parseDouble(params[2]);
            List<Double> serviceResponse = service.transformThreeRealNumbers(a, b, c);
            NumberFormat numberFormat = TextManager.getCurrentNumberFormat();
            String firstNumber = numberFormat.format(serviceResponse.get(0));
            String secondNumber = numberFormat.format(serviceResponse.get(1));
            String thirdNumber = numberFormat.format(serviceResponse.get(2));
            result = new CommandResult(
                    CommandStatus.OK,
                    TextManager.getText("result") +
                            DELIMITER +
                            "a = " + firstNumber +
                            DELIMITER +
                            "b = " + secondNumber +
                            DELIMITER +
                            "c = " + thirdNumber
            );
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
