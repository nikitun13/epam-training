package by.training.branching.controller.command.impl;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.service.OddNumberFinderService;
import by.training.branching.service.factory.ServiceFactory;
import by.training.branching.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;

/**
 * The class {@code OddSumCommand} is a class that implements {@link Command}.
 * Calls the tasks of {@link OddNumberFinderService}.
 *
 * @author Nikita Romanov
 * @see OddNumberFinderService
 */
public class OddSumCommand implements Command {

    private static final Logger logger = LogManager.getLogger(OddSumCommand.class);
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 99;

    private final OddNumberFinderService service =
            ServiceFactory.getInstance().getOddNumberFinderService();

    @Override
    public CommandResult execute(String paramsLine) {
        logger.debug("received params: {}", paramsLine);
        CommandResult result;
        if (paramsLine.isBlank()) {
            result = calculate();
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

    private CommandResult calculate() {
        long serviceResponse = service.findSumOfOddNumbersInRange(LOWER_BOUND, UPPER_BOUND);
        NumberFormat numberFormat = TextManager.getCurrentNumberFormat();
        return new CommandResult(
                CommandStatus.OK,
                TextManager.getText("result") +
                        DELIMITER +
                        numberFormat.format(serviceResponse)
        );
    }
}
