package by.training.classes.controller.command;

import by.training.classes.controller.command.result.CommandResult;

/**
 * Describes the interface of a command that executes task.
 *
 * @author Nikita Romanov
 */
@FunctionalInterface
public interface Command {

    /**
     * Executes task.
     *
     * @param params array of string parameters.
     * @return {@code CommandResult} that contains status and message.
     * @see CommandResult
     */
    CommandResult execute(String[] params);
}
