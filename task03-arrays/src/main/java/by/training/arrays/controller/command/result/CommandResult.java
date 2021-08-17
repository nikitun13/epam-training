package by.training.arrays.controller.command.result;

import java.util.Objects;
import java.util.Optional;

/**
 * The class {@code CommandResult} is a class
 * that shown a command execution result.<br/>
 * It contains {@code CommandStatus}
 * and {@code message} with description of result.
 *
 * @author Nikita Romanov
 * @see CommandStatus
 */
public class CommandResult {

    private String message;
    private CommandStatus status;

    public CommandResult(CommandStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CommandResult(CommandStatus status) {
        this.status = status;
    }

    public CommandStatus getStatus() {
        return status;
    }

    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

    public void setStatus(CommandStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResult result = (CommandResult) o;
        return Objects.equals(message, result.message)
                && status == result.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, status);
    }

    @Override
    public String toString() {
        return "CommandResult{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
