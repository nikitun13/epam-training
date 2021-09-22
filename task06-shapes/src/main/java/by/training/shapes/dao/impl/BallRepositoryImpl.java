package by.training.shapes.dao.impl;

import by.training.shapes.dao.Repository;
import by.training.shapes.dao.specification.Specification;
import by.training.shapes.dao.storage.BallStorageImpl;
import by.training.shapes.dao.storage.Storage;
import by.training.shapes.entity.Ball;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The class {@code BallRepositoryImpl} is a class
 * that implements {@link Repository}.
 *
 * @author Nikita Romanov
 * @see Repository
 */
public class BallRepositoryImpl implements Repository<Ball> {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(BallRepositoryImpl.class);
    /**
     * Message for logger about received entity.
     */
    private static final String RECEIVED_BALL_LOGGER_MESSAGE
            = "received ball: {}";

    /**
     * Storage of {@link Ball} entities.
     */
    private final Storage<Integer, Ball> storage
            = BallStorageImpl.getInstance();
    /**
     * ID generator for entities.
     */
    private int idGenerator;

    @Override
    public boolean add(final Ball entity) {
        log.debug(RECEIVED_BALL_LOGGER_MESSAGE, entity);
        int generatedId = ++idGenerator;
        entity.setId(generatedId);
        log.debug("generated id: {}", generatedId);
        return storage.add(generatedId, entity);
    }

    @Override
    public void addAll(final List<Ball> entities) {
        log.debug("received entities: {}", entities);
        entities.forEach(this::add);
    }

    @Override
    public Optional<Ball> findById(final int id) {
        log.debug("received id: {}", id);
        return Optional.ofNullable(storage.getByKey(id));
    }

    @Override
    public boolean update(final Ball entity) {
        log.debug(RECEIVED_BALL_LOGGER_MESSAGE, entity);
        return storage.update(entity.getId(), entity);
    }

    @Override
    public boolean remove(final Ball entity) {
        log.debug(RECEIVED_BALL_LOGGER_MESSAGE, entity);
        return storage.remove(entity.getId(), entity);
    }

    @Override
    public List<Ball> getAll() {
        return storage.getALlValues();
    }

    @Override
    public List<Ball> findBySpecification(
            final Specification<Ball> specification) {
        log.debug("received specification: {}", specification);
        List<Ball> specifiedBalls = new ArrayList<>(storage.getALlValues().stream()
                .filter(specification::isSpecified)
                .toList());
        specification.getOptionalComparator().ifPresent(specifiedBalls::sort);
        log.debug("specified entities: {}", specifiedBalls);
        return specifiedBalls;
    }
}
