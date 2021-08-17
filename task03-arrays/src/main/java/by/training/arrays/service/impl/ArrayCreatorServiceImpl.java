package by.training.arrays.service.impl;

import by.training.arrays.dao.ArrayDao;
import by.training.arrays.dao.DaoException;
import by.training.arrays.dao.DaoFactory;
import by.training.arrays.entity.Array;
import by.training.arrays.service.ArrayCreatorService;
import by.training.arrays.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * The class {@code ArrayCreatorServiceImpl}
 * is a class that implements {@link ArrayCreatorService}.
 *
 * @author Nikita Romanov
 */
public class ArrayCreatorServiceImpl implements ArrayCreatorService {

    private static final Logger logger =
            LogManager.getLogger(ArrayCreatorServiceImpl.class);

    private static final Random RANDOM = new Random();
    private static final String LOGGER_RESULT_MESSAGE = "result: {}";

    private final ArrayDao arrayDao = DaoFactory.getInstance().getArrayDAO();

    @Override
    public Array<Integer> fillRandomized(int size, int minValue, int maxValue) {
        logger.debug("received size = {}, minValue = {}, maxValue = {}",
                size, minValue, maxValue);
        if (size < 0) {
            throw new ServiceException("size of Array can't be " + size);
        }
        int diff = maxValue - minValue;
        if (diff < 0) {
            throw new ServiceException(String.format(
                    "maxValue %d can't be less than minValue %d",
                    maxValue, minValue)
            );
        }
        Array<Integer> array = new Array<>(size);
        for (int i = 0; i < size; i++) {
            Integer value = RANDOM.nextInt(diff + 1) + minValue;
            array.set(i, value);
        }
        logger.debug(LOGGER_RESULT_MESSAGE, array);
        return array;
    }

    @Override
    public List<Array<String>> createFromFile(Path path) {
        logger.debug("received path: {}", path);
        Objects.requireNonNull(path);
        try {
            List<Array<String>> result = arrayDao.readFromFile(path);
            logger.debug(LOGGER_RESULT_MESSAGE, result);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Array<Integer> convertToIntegerArray(Array<String> array) {
        logger.debug("received: {}", array);
        Objects.requireNonNull(array);
        int size = array.getSize();
        Array<Integer> result = new Array<>(size);
        try {
            for (int i = 0; i < size; i++) {
                String stringValue = array.get(i);
                Integer value = Integer.valueOf(stringValue);
                result.set(i, value);
            }
            logger.debug(LOGGER_RESULT_MESSAGE, result);
            return result;
        } catch (NumberFormatException e) {
            throw new ServiceException(
                    "String element of input array"
                            + " cannot be parsed as an integer", e
            );
        }
    }
}
