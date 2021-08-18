package by.training.arrays.service.impl;

import by.training.arrays.dao.DaoException;
import by.training.arrays.dao.DaoFactory;
import by.training.arrays.dao.MatrixDao;
import by.training.arrays.entity.Matrix;
import by.training.arrays.service.MatrixCreatorService;
import by.training.arrays.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * The class {@code MatrixCreatorServiceImpl}
 * is a class that implements {@link MatrixCreatorService}.
 *
 * @author Nikita Romanov
 */
public class MatrixCreatorServiceImpl implements MatrixCreatorService {

    private static final Logger logger =
            LogManager.getLogger(MatrixCreatorServiceImpl.class);

    private static final Random RANDOM = new Random();

    private final MatrixDao matrixDao = DaoFactory.getInstance().getMatrixDAO();

    @Override
    public void fillRandomized(Matrix matrix, int minValue, int maxValue) {
        logger.debug("received minValue = {}, maxValue = {} and matrix: {}",
                minValue, maxValue, matrix);
        Objects.requireNonNull(matrix);
        int diff = maxValue - minValue;
        if (diff < 0) {
            throw new ServiceException(String.format(
                    "maxValue %d can't be less than minValue %d",
                    maxValue, minValue)
            );
        }
        int rows = matrix.getNumberOfRows();
        int columns = matrix.getNumberOfColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int value = RANDOM.nextInt(diff + 1) + minValue;
                matrix.setElement(i, j, value);
            }
        }
        logger.debug("result: {}", matrix);
    }

    @Override
    public List<Matrix> createFromFile(Path path) {
        logger.debug("received path: {}", path);
        Objects.requireNonNull(path);
        try {
            List<Matrix> result = matrixDao.readAll(path);
            logger.debug("result: {}", result);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
