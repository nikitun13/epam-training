package by.training.branching.controller.command;

import by.training.branching.controller.command.result.CommandResult;

/**
 * Describes the interface of a command that executes task.
 *
 * @author Nikita Romanov
 */
@FunctionalInterface
public interface Command {

    /**
     * Delimiter for parsing {@code paramsLine}.
     */
    String DELIMITER = " ";

    /**
     * Executes task.
     *
     * @param paramsLine string of parameters.
     * @return {@code CommandResult} that contains status and message.
     * @see CommandResult
     */
    CommandResult execute(String paramsLine);

    /**
     * Splits string of params.
     *
     * @param paramsLine string of params.
     * @return array of params.
     */
    default String[] splitParams(String paramsLine) {
        return paramsLine.split(DELIMITER);
    }
}
