package by.training.arrays.service.impl.sort;

import by.training.arrays.entity.Array;
import by.training.arrays.service.ArraySortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * The class {@code ShakerSort}
 * is a class that implements {@link ArraySortingService}.<br>
 * Provides sorting by shaker method.
 *
 * @author Nikita Romanov
 */
public class ShakerSort implements ArraySortingService {

    private static final Logger logger =
            LogManager.getLogger(ShakerSort.class);

    @Override
    public <T extends Comparable<? super T>> void sort(Array<T> array) {
        logger.debug("received Array: {}", array);
        Objects.requireNonNull(array);
        logger.debug("array size: {}", array.getSize());
        int comparisonOperations = 0;
        int exchangeOperations = 0;
        int indexOfLastUnsortedElement = array.getSize() - 1;
        int indexOfFirstUnsortedElement = 0;
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = indexOfFirstUnsortedElement + 1;
                 i <= indexOfLastUnsortedElement; i++) {
                T left = array.get(i - 1);
                T right = array.get(i);
                comparisonOperations++;
                if (right.compareTo(left) < 0) {
                    exchangeAdjacent(array, i);
                    exchangeOperations++;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            indexOfLastUnsortedElement--;
            swapped = false;

            for (int i = indexOfLastUnsortedElement - 1;
                 i >= indexOfFirstUnsortedElement; i--) {
                T left = array.get(i);
                T right = array.get(i + 1);
                if (right.compareTo(left) < 0) {
                    exchangeAdjacent(array, i + 1);
                    exchangeOperations++;
                    swapped = true;
                }
            }
            indexOfFirstUnsortedElement++;
        }

        logger.debug("comparison operations: {}", comparisonOperations);
        logger.debug("exchange operations: {}", exchangeOperations);
        logger.debug("sort result: {}", array);
    }

    private <T> void exchangeAdjacent(Array<T> array, int index) {
        T temp = array.get(index);
        array.set(index, array.get(index - 1));
        array.set(index - 1, temp);
    }
}
