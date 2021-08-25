package by.training.classes.dao.impl;

import by.training.classes.dao.DaoException;
import by.training.classes.dao.TextDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * The class {@code TextDaoImpl}
 * is a class that implements {@link TextDao}.
 *
 * @author Nikita Romanov
 * @see TextDao
 */
public class TextDaoImpl implements TextDao {

    @Override
    public void save(Path path, String text) throws DaoException {
        try {
            Files.write(path, text.getBytes());
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public String read(Path path) throws DaoException {
        try (Stream<String> stream = Files.lines(path)) {
            return stream.collect(joining("\n"));
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Path path) throws DaoException {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
}
