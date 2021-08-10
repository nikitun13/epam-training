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
     * Sorts elements using comparing method
     * from {@link Comparable} interface.<br>
     * The sorting type depends
     * on the specific implementation.
     *
     * @param list list of elements for sorting.
     * @param <T>  type of element for sorting.
     * @see Comparable
     */
    <T extends Comparable<? super T>> void sort(List<T> list);
}
