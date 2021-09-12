package by.training.multithreading.service;

import by.training.multithreading.service.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The class {@code ParallelMatrixFillerServiceProvider} is a class
 * that provides the implementation of {@link ParallelMatrixFillerService}.
 *
 * @author Nikita Romanov
 * @see ParallelMatrixFillerService
 */
public final class ParallelMatrixFillerServiceProvider {

    private static final ParallelMatrixFillerServiceProvider INSTANCE
            = new ParallelMatrixFillerServiceProvider();

    private static final String LOCK_FILLER_SERVICE = "lock";
    private static final String SEMAPHORE_FILLER_SERVICE = "semaphore";
    private static final String ATOMIC_FILLER_SERVICE = "atomic";
    private static final String MAP_FILLER_SERVICE = "map";
    private static final String SET_FILLER_SERVICE = "set";

    private final Map<String, ParallelMatrixFillerService> repository;

    private ParallelMatrixFillerServiceProvider() {
        repository = new HashMap<>();
        repository.put(LOCK_FILLER_SERVICE, new LockFillerService());
        repository.put(SEMAPHORE_FILLER_SERVICE, new SemaphoreFillerService());
        repository.put(ATOMIC_FILLER_SERVICE, new AtomicFillerService());
        repository.put(MAP_FILLER_SERVICE, new MapFillerService());
        repository.put(SET_FILLER_SERVICE, new SetFillerService());
    }

    public ParallelMatrixFillerService getService(String name) {
        return repository.get(name);
    }

    public static ParallelMatrixFillerServiceProvider getInstance() {
        return INSTANCE;
    }
}
