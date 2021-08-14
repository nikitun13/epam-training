package by.training.arrays.entity;

import java.util.*;

/**
 * The class {@code Array} represents entity
 * of an array.<br>
 * Contains array of elements.
 * Provides access to them through methods.
 *
 * @param <T> type of elements.
 * @author Nikita Romanov
 */
public class Array<T> implements Iterable<T> {

    /**
     * The array where elements of the {@code Array} are stored.
     */
    private final T[] elements;

    /**
     * Creates array of elements with {@code initialSize}.
     *
     * @param initialSize initial size for creation {@code Array}.
     */
    @SuppressWarnings("unchecked")
    public Array(int initialSize) {
        elements = (T[]) new Object[initialSize];
    }

    /**
     * Initializes elements of the class {@code Array}
     * using param {@code elements}.
     *
     * @param elements array of elements to initialize.
     * @throws NullPointerException if param {@code elements} is null.
     */
    public Array(T[] elements) {
        Objects.requireNonNull(elements);
        this.elements = elements.clone();
    }

    /**
     * Returns element at that {@code index}.
     *
     * @param index index of element.
     * @return element at that {@code index}.
     * @throws IndexOutOfBoundsException if the {@code index} out of bounds.
     */
    public T get(int index) {
        Objects.checkIndex(index, getSize());
        return elements[index];
    }

    /**
     * Sets a new {@code element} to the array at the given {@code index}.
     *
     * @param index   array index to set new {@code element}.
     * @param element the element to be set at this {@code index}.
     * @throws IndexOutOfBoundsException if the {@code index} out of bounds.
     */
    public void set(int index, T element) {
        Objects.checkIndex(index, getSize());
        elements[index] = element;
    }

    /**
     * Returns copy of the {@code Array}
     * with range from {@code fromIndex} to {@code toIndex}.
     *
     * @param fromIndex the initial index of the range to be copied, inclusive
     * @param toIndex   the final index of the range to be copied, exclusive.
     *                  This index may lie outside the array.
     * @return a new {@code Array} containing the specified range
     * from the original {@code Array}.
     * @throws ArrayIndexOutOfBoundsException if {@code fromIndex < 0}.
     *                                        Or
     *                                        {@code fromIndex > size of Array}.
     * @throws IllegalArgumentException       if {@code fromIndex > toIndex}.
     **/
    public Array<T> subArray(int fromIndex, int toIndex) {
        T[] newElements = Arrays.copyOfRange(elements, fromIndex, toIndex);
        return new Array<>(newElements);
    }

    public int getSize() {
        return elements.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Array<?> array = (Array<?>) o;
        return Arrays.equals(elements, array.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    /**
     * The class {@code ArrayIterator} is an iterator
     * for the {@link Array}.
     */
    private class ArrayIterator implements Iterator<T> {

        /**
         * The index of the item to return the next time
         * when {@code next} method is called.
         */
        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex != getSize();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return get(currentIndex++);
        }
    }
}
