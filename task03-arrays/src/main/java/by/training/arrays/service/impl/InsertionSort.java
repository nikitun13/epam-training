package by.training.arrays.service.impl;

import by.training.arrays.entity.Array;
import by.training.arrays.service.ArraySortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * The class {@code InsertionSort}
 * is a class that implements {@link ArraySortingService}.<br>
 * Provides sorting by insertion method.
 *
 * @author Nikita Romanov
 */
public class InsertionSort implements ArraySortingService {

    private static final Logger logger =
            LogManager.getLogger(InsertionSort.class);

    @Override
    public <T extends Comparable<? super T>> void sort(Array<T> array) {
        logger.debug("received Array: {}", array);
        Objects.requireNonNull(array);
        int comparisonOperations = 0;
        int exchangeOperations = 0;
        int size = array.getSize();

        for (int i = 1; i < size; i++) {
            for (int j = i; j > 0; j--) {
                T left = array.get(j - 1);
                T right = array.get(j);
                comparisonOperations++;
                if (right.compareTo(left) >= 0) {
                    break;
                }
                array.set(j, left);
                array.set(j - 1, right);
                exchangeOperations++;
            }
        }

        logger.debug("array size: {}", size);
        logger.debug("comparison operations: {}", comparisonOperations);
        logger.debug("exchange operations: {}", exchangeOperations);
        logger.debug("sort result: {}", array);
    }
}
