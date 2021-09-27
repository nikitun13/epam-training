package by.training.information.dao.reader;

import by.training.information.dao.DaoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * The class {@code ReaderImpl}
 * is a class that implements {@link Reader}.
 *
 * @author Nikita Romanov
 * @see Reader
 */
public final class ReaderImpl implements Reader {

    /**
     * Singleton instance.
     */
    private static ReaderImpl instance;

    private ReaderImpl() {
    }

    @Override
    public List<String> readAllLines(final Path path) throws DaoException {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new DaoException("IO exception occurred", e);
        }
    }

    /**
     * Getter for singleton instance.
     *
     * @return instance of this class.
     */
    public static ReaderImpl getInstance() {
        if (instance == null) {
            instance = new ReaderImpl();
        }
        return instance;
    }
}
