package by.training.branching.service.factory;

import by.training.branching.service.FunctionCalculatorService;
import by.training.branching.service.IntegerComparatorService;
import by.training.branching.service.impl.FunctionCalculatorServiceImpl;
import by.training.branching.service.impl.IntegerComparatorServiceImpl;

/**
 * The class {@code ServiceFactory} is utility class that provides the implementation of services.
 *
 * @author Nikita Romanov
 */
public final class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final IntegerComparatorService integerComparatorService = new IntegerComparatorServiceImpl();
    private final FunctionCalculatorService functionCalculatorService = new FunctionCalculatorServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public IntegerComparatorService getIntegerComparatorService() {
        return integerComparatorService;
    }

    public FunctionCalculatorService getFunctionCalculatorService() {
        return functionCalculatorService;
    }
}
