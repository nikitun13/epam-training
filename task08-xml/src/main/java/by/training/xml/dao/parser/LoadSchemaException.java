package by.training.xml.dao.parser;

public class LoadSchemaException extends RuntimeException {

    public LoadSchemaException() {
    }

    public LoadSchemaException(String message) {
        super(message);
    }

    public LoadSchemaException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadSchemaException(Throwable cause) {
        super(cause);
    }
}
