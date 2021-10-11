package by.training.xml.service;

/**
 * The class {@code ServiceException}
 * is a class that implements {@link Exception}.<br/>
 * This exception is thrown by service classes.
 *
 * @author Nikita Romanov
 */
public class ServiceException extends Exception {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
