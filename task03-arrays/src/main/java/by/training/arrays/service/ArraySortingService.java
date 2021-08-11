package by.training.arrays.service;

import by.training.arrays.entity.Array;

/**
 * Describes the interface of a service
 * that sorts {@link Array} of {@code comparable} elements.
 *
 * @author Nikita Romanov
 * @see Array
 * @see Comparable
 */
@FunctionalInterface
public interface ArraySortingService {

    /**
     * Sorts elements using comparing method
     * from {@link Comparable} interface.<br>
     * The sorting type depends
     * on the specific implementation.
     *
     * @param array {@code Array} of elements for sorting.
     * @param <T>   type of element for sorting.
     * @throws NullPointerException if {@code array} is {@code null}.
     */
    <T extends Comparable<? super T>> void sort(Array<T> array);
}
