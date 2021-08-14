package by.training.arrays.service.impl;

import by.training.arrays.entity.Array;
import by.training.arrays.service.ArraySortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code BinaryMergeSort}
 * is a class that implements {@link ArraySortingService}.<br>
 * Provides sorting by binary merge method.<br>
 * The implementation was taken from
 * <a href="https://www.baeldung.com/java-merge-sort">Baeldung</a>
 *
 * @author Nikita Romanov
 */
public class BinaryMergeSort implements ArraySortingService {

    private static final Logger logger =
            LogManager.getLogger(BinaryMergeSort.class);

    @Override
    public <T extends Comparable<? super T>> void sort(Array<T> array) {
        logger.debug("received Array: {}", array);
        int size = array.getSize();
        if (size < 2) {
            return;
        }
        int middle = size / 2;

        Array<T> left = array.subArray(0, middle);
        Array<T> right = array.subArray(middle, size);

        sort(left);
        sort(right);

        merge(array, left, right);
        logger.debug("result: {}", array);
    }

    private <T extends Comparable<? super T>> void merge(Array<T> array, Array<T> left, Array<T> right) {
        int i = 0;
        int j = 0;
        int k = 0;
        int leftSize = left.getSize();
        int rightSize = right.getSize();
        while (i < leftSize && j < rightSize) {
            T leftElement = left.get(i);
            T rightElement = right.get(j);
            if (leftElement.compareTo(rightElement) <= 0) {
                array.set(k, leftElement);
                i++;
            } else {
                array.set(k, rightElement);
                j++;
            }
            k++;
        }
        while (i < leftSize) {
            T element = left.get(i);
            array.set(k, element);
            k++;
            i++;
        }
        while (j < rightSize) {
            T element = right.get(j);
            array.set(k, element);
            k++;
            j++;
        }
    }
}
