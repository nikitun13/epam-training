package by.training.arrays.service.impl.sort;

import by.training.arrays.entity.Array;
import by.training.arrays.service.ArraySortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * The class {@code BubbleSort}
 * is a class that implements {@link ArraySortingService}.<br>
 * Provides sorting by bubble method.
 *
 * @author Nikita Romanov
 */
public class BubbleSort implements ArraySortingService {

    private static final Logger logger =
            LogManager.getLogger(BubbleSort.class);

    @Override
    public <T extends Comparable<? super T>> void sort(Array<T> array) {
        logger.debug("received Array: {}", array);
        Objects.requireNonNull(array);
        int comparisonOperations = 0;
        int exchangeOperations = 0;
        int size = array.getSize();

        for (int i = 0; i < size - 1; i++) {
            for (int j = 1; j < size - i; j++) {
                T left = array.get(j - 1);
                T right = array.get(j);
                comparisonOperations++;
                if (right.compareTo(left) < 0) {
                    array.set(j, left);
                    array.set(j - 1, right);
                    exchangeOperations++;
                }
            }
        }

        logger.debug("array size: {}", size);
        logger.debug("comparison operations: {}", comparisonOperations);
        logger.debug("exchange operations: {}", exchangeOperations);
        logger.debug("sort result: {}", array);
    }
}
