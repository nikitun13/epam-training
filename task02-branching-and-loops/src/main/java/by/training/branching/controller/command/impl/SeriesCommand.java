package by.training.branching.controller.command.impl;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.service.NumberSeriesService;
import by.training.branching.service.ServiceException;
import by.training.branching.service.factory.ServiceFactory;
import by.training.branching.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;
import java.util.Arrays;

/**
 * The class {@code SeriesCommand} is a class that implements {@link Command}.
 * Calls the tasks of {@link NumberSeriesService}.
 *
 * @author Nikita Romanov
 * @see NumberSeriesService
 */
public class SeriesCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SeriesCommand.class);

    private static final int LAST_POWER = 10;
    private static final String DOUBLE_REGEX = "[+-]?\\d+(\\.\\d+)?";


    private final NumberSeriesService service =
            ServiceFactory.getInstance().getNumberSeriesService();

    @Override
    public CommandResult execute(String paramsLine) {
        logger.debug("received params: {}", paramsLine);
        CommandResult result;
        String[] params = splitParams(paramsLine);
        String arrayStatus = Arrays.toString(params);
        logger.debug("split params: {}", arrayStatus);
        if (paramsLine.isBlank()) {
            result = powersOfTwo();
        } else if (isValidNumberOfParams(params)) {
            result = calcSeries(params[0]);
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

    private CommandResult powersOfTwo() {
        long sum = service.calculateSumOfPowersOfTwo(LAST_POWER);
        NumberFormat numberFormat = TextManager.getCurrentNumberFormat();
        return new CommandResult(
                CommandStatus.OK,
                TextManager.getText("result") +
                        DELIMITER +
                        numberFormat.format(sum)
        );
    }

    private CommandResult calcSeries(String param) {
        CommandResult result;
        if (isDouble(param)) {
            double e = Double.parseDouble(param);
            try {
                double sum = service.calculateNumberSeries(e);
                NumberFormat numberFormat = TextManager.getCurrentNumberFormat();
                result = new CommandResult(
                        CommandStatus.OK,
                        TextManager.getText("result") +
                                DELIMITER +
                                numberFormat.format(sum)
                );
            } catch (ServiceException ex) {
                logger.error(ex);
                result = new CommandResult(
                        CommandStatus.ERROR,
                        TextManager.getText("series.error.eLessThanZero")
                );
            }
        } else {
            result = new CommandResult(
                    CommandStatus.ERROR,
                    TextManager.getText("error.invalidParameters")
            );
            logger.error("Invalid parameter: {}", param);
        }
        return result;
    }

    private boolean isValidNumberOfParams(String[] params) {
        return params.length == 1;
    }

    private boolean isDouble(String param) {
        return param.matches(DOUBLE_REGEX);
    }
}
