package by.training.arrays.service.impl.sort;

import by.training.arrays.service.ArraySortingService;
import by.training.arrays.service.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The class {@code ArraySortingServiceProvider} is utility class
 * that provides the implementation of {@link ArraySortingService}.
 *
 * @author Nikita Romanov
 * @see ArraySortingService
 */
public final class ArraySortingServiceProvider {

    private static final ArraySortingServiceProvider INSTANCE =
            new ArraySortingServiceProvider();

    public static final String BUBBLE = "bubble";
    public static final String SHAKER = "shaker";
    public static final String SELECTION = "selection";
    public static final String BINARY_INSERTION = "binary-insertion";
    public static final String INSERTION = "insertion";
    public static final String BINARY_MERGE = "binary-merge";
    public static final String SHELL = "shell";

    private final Map<String, ArraySortingService> repository = new HashMap<>();

    private ArraySortingServiceProvider() {
        repository.put(BUBBLE, new BubbleSort());
        repository.put(SHAKER, new ShakerSort());
        repository.put(SELECTION, new SelectionSort());
        repository.put(BINARY_INSERTION, new BinaryInsertionSort());
        repository.put(INSERTION, new InsertionSort());
        repository.put(BINARY_MERGE, new BinaryMergeSort());
        repository.put(SHELL, new ShellSort());
    }

    public static ArraySortingServiceProvider getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the sorting implementation corresponding to its name.
     *
     * @param sortName name of the soring implementation.
     * @return the sorting implementation corresponding to its name.
     * @throws NullPointerException if {@code sortName} is {@code null}.
     * @throws ServiceException     if no such implementation
     *                              for {@code sortName}.
     */
    public ArraySortingService getService(String sortName) {
        Objects.requireNonNull(sortName);
        ArraySortingService service = repository.get(sortName);
        if (service == null) {
            throw new ServiceException("invalid name of soring service");
        }
        return service;
    }
}
