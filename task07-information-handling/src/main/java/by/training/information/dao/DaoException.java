package by.training.information.dao;

/**
 * The class {@code DaoException}
 * is a class that implements {@link Exception}.<br/>
 * This exception is thrown by dao classes.
 *
 * @author Nikita Romanov
 */
public class DaoException extends Exception {

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
