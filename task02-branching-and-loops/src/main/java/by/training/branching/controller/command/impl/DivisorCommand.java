package by.training.branching.controller.command.impl;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.service.DivisorService;
import by.training.branching.service.ServiceException;
import by.training.branching.service.factory.ServiceFactory;
import by.training.branching.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class {@code DivisorCommand} is a class that implements {@link Command}.
 * Calls the tasks of {@link DivisorService}.
 *
 * @author Nikita Romanov
 * @see DivisorService
 */
public class DivisorCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DivisorCommand.class);

    private static final String INT_REGEX = "[-+]?\\d+";

    private final DivisorService service =
            ServiceFactory.getInstance().getDivisorService();

    @Override
    public CommandResult execute(String paramsLine) {
        logger.debug("received params: {}", paramsLine);
        if (paramsLine.isBlank()) {
            logger.error("Invalid number of parameters");
            CommandResult result = new CommandResult(
                    CommandStatus.ERROR,
                    TextManager.getText("error.invalidNumberOfParameters")
            );
            logger.debug("result: {}", result);
            return result;
        }
        CommandResult result;
        String[] params = splitParams(paramsLine);
        String arrayStatus = Arrays.toString(params);
        logger.debug("split params: {}", arrayStatus);
        result = switch (params.length) {
            case 1 -> allNumbersDivisibleByTheirDigits(params);
            case 4 -> divisibleNumbers(params);
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

    private CommandResult divisibleNumbers(String[] params) {
        CommandResult result;
        if (isValidParams(params)) {
            int divisor = Integer.parseInt(params[0]);
            int a = Integer.parseInt(params[1]);
            int b = Integer.parseInt(params[2]);
            int c = Integer.parseInt(params[3]);
            List<Integer> input = List.of(a, b, c);
            try {
                List<Integer> serviceResponse = service.findDivisibleNumbers(divisor, input);
                result = new CommandResult(
                        CommandStatus.OK,
                        TextManager.getText("result") +
                                DELIMITER +
                                makeResultString(serviceResponse)
                );
            } catch (ServiceException e) {
                logger.error(e);
                result = new CommandResult(
                        CommandStatus.ERROR,
                        TextManager.getText("divisor.error.divisorIsZero")
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

    private CommandResult allNumbersDivisibleByTheirDigits(String[] params) {
        CommandResult result;
        if (isValidParams(params)) {
            int bound = Integer.parseInt(params[0]);
            try {
                List<Integer> serviceResponse = service.findAllNumbersDivisibleByTheirDigits(bound);
                result = new CommandResult(
                        CommandStatus.OK,
                        TextManager.getText("result") +
                                DELIMITER +
                                makeResultString(serviceResponse)
                );
            } catch (ServiceException e) {
                logger.error(e);
                result = new CommandResult(
                        CommandStatus.ERROR,
                        TextManager.getText("divisor.error.boundLessThanZero")
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

    private String makeResultString(List<Integer> list) {
        NumberFormat numberFormat = TextManager.getCurrentNumberFormat();
        return list.stream()
                .map(numberFormat::format)
                .collect(Collectors.joining(", "));
    }
}
