package by.training.arrays.service.impl.sort;

import by.training.arrays.entity.Array;
import by.training.arrays.service.ArraySortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

/**
 * The class {@code BinaryInsertionSort}
 * is a class that implements {@link ArraySortingService}.<br>
 * Provides sorting by binary insertion method.
 *
 * @author Nikita Romanov
 */
public class BinaryInsertionSort implements ArraySortingService {

    private static final Logger logger =
            LogManager.getLogger(BinaryInsertionSort.class);

    @Override
    public <T extends Comparable<? super T>> void sort(Array<T> array) {
        logger.debug("received Array: {}", array);
        Objects.requireNonNull(array);
        int comparisonOperations = 0;
        int exchangeOperations = 0;
        int size = array.getSize();

        for (int i = 1; i < size; i++) {
            T currentElement = array.get(i);
            List<Integer> findPositionResult =
                    findPosition(array, i, currentElement);
            int position = findPositionResult.get(0);
            comparisonOperations += findPositionResult.get(1);
            logger.debug("found position for element {}: {}",
                    currentElement, position
            );
            for (int j = i; j > position; j--) {
                array.set(j, array.get(j - 1));
                exchangeOperations++;
            }
            array.set(position, currentElement);
            exchangeOperations++;
        }

        logger.debug("array size: {}", size);
        logger.debug("comparison operations: {}", comparisonOperations);
        logger.debug("exchange operations: {}", exchangeOperations);
        logger.debug("sort result: {}", array);
    }

    private <T extends Comparable<? super T>> List<Integer> findPosition(
            Array<T> array, int toIndex, T element) {
        int counter = 0;
        int start = 0;
        int end = toIndex;
        while (start <= end) {
            if (start == toIndex) {
                break;
            }
            int middle = start + (end - start) / 2;
            if (element.compareTo(array.get(middle)) < 0) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
            counter++;
        }
        return List.of(start, counter);
    }
}
