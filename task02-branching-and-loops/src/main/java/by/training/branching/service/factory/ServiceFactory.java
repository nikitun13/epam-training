package by.training.branching.service.factory;

import by.training.branching.service.*;
import by.training.branching.service.impl.*;

/**
 * The class {@code ServiceFactory} is utility class that provides the implementation of services.
 *
 * @author Nikita Romanov
 */
public final class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final IntegerComparatorService integerComparatorService = new IntegerComparatorServiceImpl();
    private final FunctionCalculatorService functionCalculatorService = new FunctionCalculatorServiceImpl();
    private final RealNumberTransformerService realNumberTransformerService = new RealNumberTransformerServiceImpl();
    private final DivisorService divisorService = new DivisorServiceImpl();
    private final OddNumberFinderService oddNumberFinderService = new OddNumberFinderServiceImpl();
    private final RomanNumbersService romanNumbersService = new RomanNumbersServiceImpl();
    private final NumberSeriesService numberSeriesService = new NumberSeriesServiceImpl();

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

    public RealNumberTransformerService getRealNumberTransformerService() {
        return realNumberTransformerService;
    }

    public DivisorService getDivisorService() {
        return divisorService;
    }

    public OddNumberFinderService getOddNumberFinderService() {
        return oddNumberFinderService;
    }

    public RomanNumbersService getRomanNumbersService() {
        return romanNumbersService;
    }

    public NumberSeriesService getNumberSeriesService() {
        return numberSeriesService;
    }
}
