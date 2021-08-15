package by.training.arrays.service.impl.sort;

import by.training.arrays.entity.Array;
import by.training.arrays.service.ArraySortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * The class {@code SelectionSort}
 * is a class that implements {@link ArraySortingService}.<br>
 * Provides sorting by selection method.
 *
 * @author Nikita Romanov
 */
public class SelectionSort implements ArraySortingService {

    private static final Logger logger =
            LogManager.getLogger(SelectionSort.class);

    @Override
    public <T extends Comparable<? super T>> void sort(Array<T> array) {
        logger.debug("received Array: {}", array);
        Objects.requireNonNull(array);
        int comparisonOperations = 0;
        int exchangeOperations = 0;
        int size = array.getSize();
        for (int i = 0; i < size - 1; i++) {
            int indexOfMin = i;
            for (int j = i + 1; j < size; j++) {
                T minElement = array.get(indexOfMin);
                T currentElement = array.get(j);
                if (currentElement.compareTo(minElement) < 0) {
                    indexOfMin = j;
                }
                comparisonOperations++;
            }
            if (i != indexOfMin) {
                exchange(array, i, indexOfMin);
                exchangeOperations++;
            }
        }
        logger.debug("array size: {}", size);
        logger.debug("comparison operations: {}", comparisonOperations);
        logger.debug("exchange operations: {}", exchangeOperations);
        logger.debug("sort result: {}", array);
    }

    private <T> void exchange(Array<T> array, int firstIndex, int secondIndex) {
        T temp = array.get(firstIndex);
        array.set(firstIndex, array.get(secondIndex));
        array.set(secondIndex, temp);
    }
}
