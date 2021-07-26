package by.training.linear.exception;

public class InvalidCircleException extends RuntimeException {

    public InvalidCircleException() {
    }

    public InvalidCircleException(String message) {
        super(message);
    }

    public InvalidCircleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCircleException(Throwable cause) {
        super(cause);
    }
}
