package by.training.shapes.service.impl;

import by.training.shapes.dao.BallDao;
import by.training.shapes.dao.DaoException;
import by.training.shapes.dao.DaoFactory;
import by.training.shapes.dao.Repository;
import by.training.shapes.dao.specification.Specification;
import by.training.shapes.entity.Ball;
import by.training.shapes.service.EntityService;
import by.training.shapes.service.ServiceException;
import by.training.shapes.service.validator.BallValidatorImpl;
import by.training.shapes.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * The class {@code BallServiceImpl}
 * is a class that implements {@link EntityService}.
 *
 * @author Nikita Romanov
 * @see EntityService
 */
public class BallServiceImpl implements EntityService<Ball> {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(BallServiceImpl.class);

    /**
     * Ball repository.
     */
    private final Repository<Ball> repository;
    /**
     * Ball DAO.
     */
    private final BallDao dao;
    /**
     * Validator for a {@code Ball}.
     */
    private final Validator<Ball> validator
            = BallValidatorImpl.getInstance();

    /**
     * Constructor. Gets implementation of DAO's from {@link DaoFactory}.
     */
    public BallServiceImpl() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        repository = daoFactory.getBallRepository();
        dao = daoFactory.getBallDao();
    }

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
            List<Ball> entities = dao.getAllFromFile(path);
            repository.addAll(entities);
        } catch (InvalidPathException e) {
            throw new ServiceException("invalid path: " + pathToFile, e);
        } catch (DaoException e) {
            throw new ServiceException("DAO exception occurred", e);
        }
    }

    @Override
    public boolean add(final Ball entity) throws ServiceException {
        if (!validator.isValid(entity)) {
            log.debug("received invalid entity: {}", entity);
            throw new ServiceException("ball is invalid: " + entity);
        }
        return repository.add(entity);
    }

    @Override
    public void addAll(final List<Ball> entities) throws ServiceException {
        if (entities == null) {
            log.debug("received entities: null");
            throw new ServiceException("entities can't be null");
        }
        int sizeOnInput = entities.size();
        log.debug("size on input: {}", sizeOnInput);
        long sizeAfterFiltering = entities.stream()
                .filter(validator::isValid)
                .count();
        log.debug("size after filtering: {}", sizeAfterFiltering);
        if (sizeOnInput != sizeAfterFiltering) {
            log.debug("some entities are invalid: {}", entities);
            throw new ServiceException("invalid entities inside the list: "
                    + entities
            );
        }
        entities.forEach(repository::add);
    }

    @Override
    public Optional<Ball> findById(final int id) {
        return repository.findById(id);
    }

    @Override
    public boolean update(final Ball entity) throws ServiceException {
        if (!validator.isValid(entity)) {
            log.debug("received invalid entity: {}", entity);
            throw new ServiceException("ball is invalid: " + entity);
        }
        return repository.update(entity);
    }

    @Override
    public boolean remove(final Ball entity) throws ServiceException {
        if (entity == null) {
            log.debug("received entity: null");
            throw new ServiceException("ball can't be null");
        }
        return repository.remove(entity);
    }

    @Override
    public List<Ball> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Ball> findBySpecification(
            final Specification<Ball> specification) throws ServiceException {
        if (specification == null) {
            log.debug("received specification: null");
            throw new ServiceException("specification can't be null");
        }
        return repository.findBySpecification(specification);
    }
}
