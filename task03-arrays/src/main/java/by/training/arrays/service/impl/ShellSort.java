package by.training.arrays.service.impl;

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
        int size = array.getSize();
        for (int gap = size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i++) {
                T key = array.get(i);
                int j = i;
                while (j >= gap && key.compareTo(array.get(j - gap)) < 0) {
                    array.set(j, array.get(j - gap));
                    j -= gap;
                }
                array.set(j, key);
            }
        }
        logger.debug("array size: {}", size);
        logger.debug("sort result: {}", array);
    }
}
