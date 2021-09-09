package by.training.multithreading.dao.impl;

import by.training.multithreading.dao.DaoException;
import by.training.multithreading.dao.MatrixDao;
import by.training.multithreading.entity.Matrix;
import by.training.multithreading.entity.MatrixException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * The class {@code ArrayDaoImpl}
 * is a class that implements {@link MatrixDao}.
 * Provides access to {@code Matrix} from file.
 *
 * @author Nikita Romanov
 */
public class MatrixDaoImpl implements MatrixDao {

    private static final Logger log
            = LogManager.getLogger(MatrixDaoImpl.class);

    private static final String SEPARATOR = "\\s+";

    @Override
    public Matrix read(Path path) throws DaoException {
        log.debug("received path: {}", path);
        try (Stream<String> stream = Files.lines(path)) {
            int[][] elements = stream
                    .map(line -> line.split(SEPARATOR))
                    .map(array -> Arrays.stream(array)
                            .mapToInt(Integer::parseInt)
                            .toArray()
                    )
                    .toArray(int[][]::new);
            Matrix result = new Matrix(elements);
            log.debug("result: {}", result);
            return result;
        } catch (IOException e) {
            throw new DaoException("IO exception occurred", e);
        } catch (NumberFormatException | MatrixException e) {
            throw new DaoException("invalid data in the file", e);
        }
    }
}
