package by.training.arrays.service.impl.sort;

import by.training.arrays.entity.Array;
import by.training.arrays.service.ArraySortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * The class {@code ShellSort}
 * is a class that implements {@link ArraySortingService}.<br>
 * Provides sorting by Shell's method.<br>
 * The implementation was taken from
 * <a href="https://www.baeldung.com/java-shell-sort">Baeldung</a>
 *
 * @author Nikita Romanov
 */
public class ShellSort implements ArraySortingService {

    private static final Logger logger =
            LogManager.getLogger(ShellSort.class);

    @Override
    public <T extends Comparable<? super T>> void sort(Array<T> array) {
        logger.debug("received Array: {}", array);
        Objects.requireNonNull(array);
        int comparisonOperations = 0;
        int exchangeOperations = 0;
        int size = array.getSize();
        for (int gap = size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i++) {
                T key = array.get(i);
                int j = i;
                while (j >= gap) {
                    T el = array.get(j - gap);
                    comparisonOperations++;
                    if (el.compareTo(key) < 0) {
                        break;
                    }
                    array.set(j, el);
                    exchangeOperations++;
                    j -= gap;
                }
                array.set(j, key);
                exchangeOperations++;
            }
        }
        logger.debug("array size: {}", size);
        logger.debug("comparison operations: {}", comparisonOperations);
        logger.debug("exchange operations: {}", exchangeOperations);
        logger.debug("sort result: {}", array);
    }
}
