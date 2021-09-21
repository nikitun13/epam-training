package by.training.shapes.service.impl;

import by.training.shapes.dao.DaoException;
import by.training.shapes.dao.DaoFactory;
import by.training.shapes.entity.Ball;
import by.training.shapes.service.RepositoryFillerService;
import by.training.shapes.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

/**
 * The class {@code BallRepositoryFillerServiceImpl}
 * is a class that implements {@link RepositoryFillerService}.
 *
 * @author Nikita Romanov
 * @see RepositoryFillerService
 */
public class BallRepositoryFillerServiceImpl
        implements RepositoryFillerService {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(BallRepositoryFillerServiceImpl.class);
    /**
     * Factory of DAOs.
     */
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public void addEntitiesFromFile(final String pathToFile)
            throws ServiceException {
        log.debug("received pathToFile: {}", pathToFile);
        if (pathToFile == null) {
            throw new ServiceException("path can't be null");
        }
        try {
            Path path = Path.of(pathToFile);
            log.debug("path: {}", path);
            List<Ball> entities = daoFactory.getBallDao().getAllFromFile(path);
            daoFactory.getBallRepository().addAll(entities);
        } catch (InvalidPathException e) {
            throw new ServiceException("invalid path: " + pathToFile, e);
        } catch (DaoException e) {
            throw new ServiceException("DAO exception occurred", e);
        }
    }
}
