package by.training.arrays.dao.impl;

import by.training.arrays.dao.ArrayDao;
import by.training.arrays.dao.DaoException;
import by.training.arrays.entity.Array;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

/**
 * The class {@code ArrayDaoImpl}
 * is a class that implements {@link ArrayDao}.
 *
 * @author Nikita Romanov
 */
public class ArrayDaoImpl implements ArrayDao {

    private static final String SEPARATOR = "\\s+";

    @Override
    public List<Array<String>> readAll(Path path) {
        try (Stream<String> stream = Files.lines(path)) {
            return stream
                    .filter(not(String::isBlank))
                    .map(String::strip)
                    .map(line -> line.split(SEPARATOR))
                    .map(Array::new)
                    .toList();
        } catch (IOException e) {
            throw new DaoException("IO exception occurred", e);
        }
    }
}
