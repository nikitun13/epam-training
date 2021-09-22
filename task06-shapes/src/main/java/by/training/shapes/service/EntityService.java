package by.training.shapes.service;

import by.training.shapes.dao.specification.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Describes the interface of a service that
 * provides access to entity storage.
 *
 * @param <T> type of {@code entity}.
 * @author Nikita Romanov
 */
public interface EntityService<T> {

    /**
     * Adds entities from the file to the entity repository.
     *
     * @param pathToFile path to file with data.
     * @throws ServiceException if DAO exception occurred
     *                          or {@code pathToFile}
     *                          is {@code null} or invalid.
     */
    void addEntitiesFromFile(String pathToFile) throws ServiceException;

    /**
     * Adds new {@code entity} to the repository.<br/>
     * Assigns {@code id} to this entity.
     *
     * @param entity new {@code entity} to be added.
     * @return {@code true} if {@code entity} was added successfully,
     * {@code false} otherwise.
     * @throws ServiceException if {@code entity} is {@code null}
     *                          or {@code entity} is invalid.
     */
    boolean add(T entity) throws ServiceException;

    /**
     * Adds new {@code entities} to the repository.<br/>
     * Assigns {@code id} to entities.
     *
     * @param entities new {@code entities} to be added.
     * @throws ServiceException if {@code entities} is {@code null}
     *                          or {@code entity} in the list is invalid.
     */
    void addAll(List<T> entities) throws ServiceException;

    /**
     * Finds {@code entity} from repository by its {@code id}.
     *
     * @param id {@code id} of the entity.
     * @return optional of entity with given {@code id}.
     */
    Optional<T> findById(int id);

    /**
     * Updates data of the {@code entity}.
     *
     * @param entity updated {@code entity}.
     * @return {@code true} if {@code entity} was updated successfully,
     * {@code false} otherwise.
     * @throws ServiceException if {@code entity} is {@code null}
     *                          or {@code entity} is invalid.
     */
    boolean update(T entity) throws ServiceException;

    /**
     * Removes {@code entity} from the repository.
     *
     * @param entity {@code entity} to be deleted.
     * @return {@code true} if {@code entity} was deleted successfully,
     * {@code false} otherwise.
     * @throws ServiceException if {@code entity} is {@code null}.
     */
    boolean remove(T entity) throws ServiceException;

    /**
     * Finds all entities from the repository.
     *
     * @return list of all entities from the repository.
     */
    List<T> getAll();

    /**
     * Finds {@code entity} from repository
     * by given {@code specification}.
     *
     * @param specification specification for filtering data.
     * @return list of entities matching the {@code specification}.
     * @throws ServiceException if {@code specification} is {@code null}.
     * @see Specification
     */
    List<T> findBySpecification(Specification<T> specification)
            throws ServiceException;
}
