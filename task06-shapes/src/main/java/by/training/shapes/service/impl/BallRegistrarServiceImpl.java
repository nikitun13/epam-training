package by.training.shapes.service.impl;

import by.training.shapes.dao.storage.BallRegistrarStorageImpl;
import by.training.shapes.dao.storage.Storage;
import by.training.shapes.entity.Ball;
import by.training.shapes.entity.BallRegistrar;
import by.training.shapes.service.RegistrarService;
import by.training.shapes.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * The class {@code BallRegistrarServiceImpl}
 * is a class that implements {@link RegistrarService}.
 *
 * @author Nikita Romanov
 * @see RegistrarService
 */
public class BallRegistrarServiceImpl
        implements RegistrarService<Ball, BallRegistrar> {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(BallRegistrarServiceImpl.class);

    /**
     * {@link BallRegistrar} storage.
     */
    private final Storage<Integer, BallRegistrar> storage
            = BallRegistrarStorageImpl.getInstance();

    @Override
    public Optional<BallRegistrar> getRegistrar(final Ball entity)
            throws ServiceException {
        log.debug("received entity: {}", entity);
        if (entity == null) {
            throw new ServiceException("ball can't be null");
        }
        BallRegistrar result = storage.getByKey(entity.getId());
        log.debug("result: {}", result);
        return Optional.ofNullable(result);
    }
}
