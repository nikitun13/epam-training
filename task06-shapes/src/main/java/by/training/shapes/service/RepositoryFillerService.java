package by.training.shapes.service;

/**
 * Describes the interface of a service that
 * fills entity repository with entities from the file.
 *
 * @author Nikita Romanov
 */
public interface RepositoryFillerService {

    /**
     * Adds entities from the file to the entity repository.
     *
     * @param pathToFile path to file with data.
     * @throws ServiceException if DAO exception occurred.
     */
    void addEntitiesFromFile(String pathToFile) throws ServiceException;
}
