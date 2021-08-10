package by.training.arrays.service;

import java.util.List;

/**
 * Describes the interface of a service
 * that sorts list of {@code comparable} elements.
 *
 * @author Nikita Romanov
 */
@FunctionalInterface
public interface SortingService {

    /**
     * Sorts elements in ascending order.
     * The sorting type depends on the specific implementation.
     *
     * @param list list of elements for sorting.
     * @param <T>  type of element for sorting.
     * @return sorted list of elements.
     */
    <T extends Comparable<? super T>> List<T> sort(List<T> list);
}
