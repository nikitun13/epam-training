package by.training.multithreading.service.impl;

import by.training.multithreading.dao.DaoException;
import by.training.multithreading.dao.DaoFactory;
import by.training.multithreading.dao.MatrixDao;
import by.training.multithreading.entity.Matrix;
import by.training.multithreading.service.MatrixCreatorService;
import by.training.multithreading.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/**
 * The class {@code MatrixCreatorServiceImpl}
 * is a class that implements {@link MatrixCreatorService}.
 * Uses {@link MatrixDao} to extract {@link Matrix} from the file.
 *
 * @author Nikita Romanov
 * @see MatrixCreatorService
 * @see MatrixDao
 * @see Matrix
 */
public class MatrixCreatorServiceImpl implements MatrixCreatorService {

    private static final Logger log
            = LogManager.getLogger(MatrixCreatorServiceImpl.class);

    private final MatrixDao dao = DaoFactory.getInstance().getMatrixDAO();

    @Override
    public Matrix createFromFile(String pathToFile) throws ServiceException {
        log.debug("received path to file: {}", pathToFile);
        if (pathToFile == null) {
            throw new ServiceException("pathToFile can't be null");
        }
        try {
            Path path = Path.of(pathToFile);
            log.debug("converted to Path: {}", path);
            Matrix result = dao.read(path);
            log.debug("result: {}", result);
            return result;
        } catch (DaoException e) {
            throw new ServiceException("dao exception occurred", e);
        } catch (InvalidPathException e) {
            throw new ServiceException("invalid path {}", e);
        }
    }
}
