package by.training.branching.controller.command.impl;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.entity.RomanInteger;
import by.training.branching.service.RomanNumbersService;
import by.training.branching.service.ServiceException;
import by.training.branching.service.factory.ServiceFactory;
import by.training.branching.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;
import java.util.Arrays;

/**
 * The class {@code ConvertCommand} is a class that implements {@link Command}.
 * Calls the tasks of {@link RomanNumbersService}.
 *
 * @author Nikita Romanov
 * @see RomanNumbersService
 */
public class ConvertCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ConvertCommand.class);

    private final RomanNumbersService service =
            ServiceFactory.getInstance().getRomanNumbersService();

    @Override
    public CommandResult execute(String paramsLine) {
        logger.debug("received params: {}", paramsLine);
        CommandResult result;
        String[] params = splitParams(paramsLine);
        String arrayStatus = Arrays.toString(params);
        logger.debug("split params: {}", arrayStatus);
        if (!paramsLine.isBlank() && isValidNumberOfParams(params)) {
            result = convert(params);
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

    private CommandResult convert(String[] params) {
        CommandResult result;
        String romanString = params[0].toUpperCase();
        RomanInteger romanInteger = new RomanInteger(romanString);
        try {
            int arabicNumber = service.fromRoman(romanInteger);
            NumberFormat numberFormat = TextManager.getCurrentNumberFormat();
            result = new CommandResult(
                    CommandStatus.OK,
                    romanString +
                            DELIMITER +
                            "=" +
                            DELIMITER +
                            numberFormat.format(arabicNumber)
            );
        } catch (ServiceException e) {
            logger.error(e);
            result = new CommandResult(
                    CommandStatus.ERROR,
                    romanString +
                            DELIMITER +
                            TextManager.getText("convert.error.invalid")
            );
        }
        return result;
    }

    private boolean isValidNumberOfParams(String[] params) {
        return params.length == 1;
    }
}
