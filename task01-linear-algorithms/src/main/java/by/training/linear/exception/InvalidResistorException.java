package by.training.linear.exception;

public class InvalidResistorException extends RuntimeException {

    public InvalidResistorException() {
    }

    public InvalidResistorException(String message) {
        super(message);
    }

    public InvalidResistorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidResistorException(Throwable cause) {
        super(cause);
    }
}
