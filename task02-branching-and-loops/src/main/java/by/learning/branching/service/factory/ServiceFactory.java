package by.learning.branching.service.factory;

import by.learning.branching.service.IntegerComparatorService;
import by.learning.branching.service.impl.IntegerComparatorServiceImpl;

/**
 * The class {@code ServiceFactory} is utility class that provides the implementation of services.
 *
 * @author Nikita Romanov
 */
public final class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final IntegerComparatorService integerComparatorService = new IntegerComparatorServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public IntegerComparatorService getIntegerComparatorService() {
        return integerComparatorService;
    }
}
