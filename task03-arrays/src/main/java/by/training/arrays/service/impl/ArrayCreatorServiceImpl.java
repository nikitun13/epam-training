package by.training.arrays.service.impl;

import by.training.arrays.entity.Array;
import by.training.arrays.service.ArrayCreatorService;
import by.training.arrays.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

/**
 * The class {@code ArrayCreatorServiceImpl}
 * is a class that implements {@link ArrayCreatorService}.
 *
 * @author Nikita Romanov
 */
public class ArrayCreatorServiceImpl implements ArrayCreatorService {

    private static final Logger logger =
            LogManager.getLogger(ArrayCreatorServiceImpl.class);

    private static final String SEPARATOR = "\\s+";
    private static final Random RANDOM = new Random();
    private static final String LOGGER_RESULT_MESSAGE = "result: {}";

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
        try (Stream<String> stream = Files.lines(path)) {
            List<Array<String>> result = stream
                    .filter(not(String::isBlank))
                    .map(String::strip)
                    .map(line -> line.split(SEPARATOR))
                    .map(Array::new)
                    .toList();
            logger.debug(LOGGER_RESULT_MESSAGE, result);
            return result;
        } catch (IOException e) {
            throw new ServiceException("IO exception occurred", e);
        } catch (RuntimeException e) {
            throw new ServiceException("invalid Array in the file", e);
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
