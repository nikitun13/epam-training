package by.training.arrays.controller.command.impl;

import by.training.arrays.controller.command.Command;
import by.training.arrays.controller.command.result.CommandResult;
import by.training.arrays.controller.command.result.CommandStatus;
import by.training.arrays.entity.Array;
import by.training.arrays.service.ArrayCreatorService;
import by.training.arrays.service.ArraySortingService;
import by.training.arrays.service.ServiceException;
import by.training.arrays.service.ServiceFactory;
import by.training.arrays.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The class {@code SortCommand} is a class
 * that implements {@link Command}.<br/>
 * Executed when the command 'sort' is received.
 * This command sorts arrays from a file
 * or generates random arrays and then sort them.
 *
 * @author Nikita Romanov
 */
public class SortCommand implements Command {

    private static final Logger logger =
            LogManager.getLogger(SortCommand.class);

    private static final String STRINGS_FROM_FILE_OPTION = "-f";
    private static final String INTEGERS_FROM_FILE_OPTION =
            STRINGS_FROM_FILE_OPTION + "i";
    private static final int FOUR_PARAMS = 4;
    private static final int THREE_PARAMS = 3;
    private static final String NEW_LINE = "\n";
    private static final String UNKNOWN_SORTING_NAME_LOGGER_MESSAGE =
            "unknown sorting name: {}";
    private static final String INT_REGEX = "[-+]?\\d+";

    private final ServiceFactory factory =
            ServiceFactory.getInstance();
    private final ArrayCreatorService arrayCreatorService =
            factory.getArrayCreatorService();

    @Override
    public CommandResult execute(String[] params) {
        String arrayStatus = Arrays.toString(params);
        logger.debug("received params: {}", arrayStatus);
        CommandResult result = switch (params.length) {
            case THREE_PARAMS -> sortFromFile(params);
            case FOUR_PARAMS -> sortRandomArray(params);
            default -> {
                logger.error("Invalid number of parameters");
                yield new CommandResult(
                        CommandStatus.INVALID_NUMBER_OF_PARAMETERS
                );
            }
        };
        logger.debug("result: {}", result);
        return result;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private CommandResult sortRandomArray(String[] params) {
        Optional<CommandResult> maybeCheckResult =
                checkParamsRandomSortInput(params);
        if (maybeCheckResult.isPresent()) {
            return maybeCheckResult.get();
        }
        String sortName = params[0];
        int size = Integer.parseInt(params[1]);
        int minValue = Integer.parseInt(params[2]);
        int maxValue = Integer.parseInt(params[3]);
        CommandResult result;
        try {
            Array<Integer> array =
                    arrayCreatorService.fillRandomized(
                            size, minValue, maxValue);
            Optional<ArraySortingService> maybeSoringService =
                    getSortingService(sortName);
            ArraySortingService sortingService = maybeSoringService.get();
            String inputArray = array.toString();
            sortingService.sort(array);
            String sortedArray = array.toString();
            result = new CommandResult(
                    CommandStatus.OK,
                    TextManager.getText("sort.generatedArray")
                            + NEW_LINE
                            + inputArray
                            + NEW_LINE
                            + TextManager.getText("sort.result")
                            + NEW_LINE
                            + sortedArray
            );
        } catch (ServiceException e) {
            logger.error("invalid parameters for fillRandomized method", e);
            result = new CommandResult(CommandStatus.INVALID_INPUT_PARAMETERS);
        }
        return result;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private CommandResult sortFromFile(String[] params) {
        logger.debug("array for sorting from file");
        String sortName = params[0];
        Optional<ArraySortingService> maybeSoringService =
                getSortingService(sortName);
        String option = params[1];
        Optional<CommandResult> maybeCheckResult =
                checkParamsForFileInput(sortName, option);
        if (maybeCheckResult.isPresent()) {
            return maybeCheckResult.get();
        }
        CommandResult result;
        String stringPath = params[2];
        try {
            Path path = Path.of(stringPath);
            List<Array<String>> arrays =
                    arrayCreatorService.createFromFile(path);
            ArraySortingService sortingService = maybeSoringService.get();
            StringBuilder inputArrays = new StringBuilder();
            StringBuilder sortedArrays;
            arrays.forEach(array -> inputArrays.append(array).append(NEW_LINE));
            if (option.equals(INTEGERS_FROM_FILE_OPTION)) {
                sortedArrays = sortAsIntegers(arrays, sortingService);
            } else {
                sortedArrays = sortAsStrings(arrays, sortingService);
            }
            result = new CommandResult(
                    CommandStatus.OK,
                    TextManager.getText("sort.inputArray")
                            + NEW_LINE
                            + inputArrays
                            + NEW_LINE
                            + TextManager.getText("sort.result")
                            + NEW_LINE
                            + sortedArrays
            );
        } catch (ServiceException e) {
            if (e.getCause().getCause() instanceof IOException) {
                logger.error("io exception occurred", e);
                result = new CommandResult(
                        CommandStatus.ERROR,
                        TextManager.getText("sort.ioException")
                );
            } else {
                logger.error("element of array can't"
                        + " be parsed as an integer", e);
                result = new CommandResult(
                        CommandStatus.ERROR,
                        TextManager.getText("sort.invalidElements")
                );
            }
        } catch (RuntimeException e) {
            logger.error("unknown error", e);
            result = new CommandResult(
                    CommandStatus.ERROR,
                    TextManager.getText("sort.unknownError")
            );
        }
        return result;
    }

    private Optional<CommandResult> checkParamsRandomSortInput(
            String[] params) {
        String sortName = params[0];
        if (getSortingService(sortName).isEmpty()) {
            logger.error(UNKNOWN_SORTING_NAME_LOGGER_MESSAGE, sortName);
            return Optional.of(new CommandResult(
                    CommandStatus.INVALID_INPUT_PARAMETERS,
                    TextManager.getText("sort.invalidSortingName")
            ));
        }
        for (int i = 1; i < params.length; i++) {
            if (!isInteger(params[i])) {
                return Optional.of(new CommandResult(
                        CommandStatus.INVALID_INPUT_PARAMETERS
                ));
            }
        }
        return Optional.empty();
    }

    private Optional<CommandResult> checkParamsForFileInput(
            String sortName, String option) {
        if (getSortingService(sortName).isEmpty()) {
            logger.error(UNKNOWN_SORTING_NAME_LOGGER_MESSAGE, sortName);
            return Optional.of(new CommandResult(
                    CommandStatus.INVALID_INPUT_PARAMETERS,
                    TextManager.getText("sort.invalidSortingName")
            ));
        }
        if (!checkOption(option)) {
            logger.error("unknown option: {}", option);
            return Optional.of(new CommandResult(
                    CommandStatus.INVALID_INPUT_PARAMETERS,
                    TextManager.getText("sort.invalidOption")
            ));
        }
        return Optional.empty();
    }

    private Optional<ArraySortingService> getSortingService(String name) {
        ArraySortingService service = null;
        try {
            service = factory.getArraySortingService(name);
        } catch (ServiceException e) {
            logger.error("unknown soring service", e);
        }
        return Optional.ofNullable(service);
    }

    private StringBuilder sortAsIntegers(List<Array<String>> arrays,
                                         ArraySortingService service) {
        StringBuilder sortResult = new StringBuilder();
        logger.debug("get as integers from file");
        for (Array<String> array : arrays) {
            Array<Integer> integers =
                    arrayCreatorService.convertToIntegerArray(array);
            service.sort(integers);
            sortResult.append(integers).append(NEW_LINE);
        }
        return sortResult;
    }

    private StringBuilder sortAsStrings(List<Array<String>> arrays,
                                        ArraySortingService sortingService) {
        StringBuilder sortResult = new StringBuilder();
        logger.debug("get as strings from file");
        for (Array<String> array : arrays) {
            sortingService.sort(array);
            sortResult.append(array).append(NEW_LINE);
        }
        return sortResult;
    }

    private boolean checkOption(String option) {
        return option.equals(STRINGS_FROM_FILE_OPTION)
                || option.equals(INTEGERS_FROM_FILE_OPTION);
    }

    private boolean isInteger(String value) {
        return value.matches(INT_REGEX);
    }
}
