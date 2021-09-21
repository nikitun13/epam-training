package by.training.shapes.dao.impl;

import by.training.shapes.dao.BallDao;
import by.training.shapes.dao.DaoException;
import by.training.shapes.dao.mapper.BallMapper;
import by.training.shapes.dao.mapper.Mapper;
import by.training.shapes.dao.reader.Reader;
import by.training.shapes.dao.reader.ReaderImpl;
import by.training.shapes.entity.Ball;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.List;

/**
 * The class {@code BallDaoImpl} is a class
 * that implements {@link BallDao}.
 *
 * @author Nikita Romanov
 * @see BallDao
 */
public class BallDaoImpl implements BallDao {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(BallDaoImpl.class);

    /**
     * {@link Reader} for getting data from the file.
     */
    private final Reader reader = ReaderImpl.getInstance();
    /**
     * {@link Mapper} for converting {@code String} data to entity.
     */
    private final Mapper<Ball> mapper = BallMapper.getInstance();

    @Override
    public List<Ball> getAllFromFile(final Path path) throws DaoException {
        List<String> allLines = reader.readAllLines(path);
        log.debug("read lines: {}", allLines);
        List<Ball> entities = allLines.stream()
                .filter(mapper::isValidLine)
                .map(mapper::mapFrom)
                .toList();
        log.debug("result: {}", entities);
        return entities;
    }
}
