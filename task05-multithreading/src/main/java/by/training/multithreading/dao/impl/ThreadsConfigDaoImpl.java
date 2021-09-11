package by.training.multithreading.dao.impl;

import by.training.multithreading.dao.DaoException;
import by.training.multithreading.dao.ThreadsConfigDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * The class {@code ThreadsConfigDaoImpl}
 * is a class that implements {@link ThreadsConfigDao}.
 *
 * @author Nikita Romanov
 */
public class ThreadsConfigDaoImpl implements ThreadsConfigDao {

    private static final Logger log
            = LogManager.getLogger(ThreadsConfigDaoImpl.class);

    @Override
    public List<Integer> readConfigs(Path path) throws DaoException {
        log.debug("received path: {}", path);
        try (Stream<String> stream = Files.lines(path)) {
            var result = stream
                    .map(Integer::valueOf)
                    .toList();
            log.debug("result: {}", result);
            return result;
        } catch (IOException e) {
            throw new DaoException("IO exception occurred", e);
        } catch (NumberFormatException e) {
            throw new DaoException("invalid data in the file", e);
        }
    }
}
