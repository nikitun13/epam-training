package by.training.shapes.controller.command.result;

/**
 * The class {@code CommandStatus} is an enumeration
 * of possible statuses of the command execution result.
 *
 * @author Nikita Romanov
 * @see CommandResult
 */
public enum CommandStatus {
    OK,
    INVALID_NUMBER_OF_PARAMETERS,
    INVALID_INPUT_PARAMETERS,
    ERROR,
    EXIT
}
