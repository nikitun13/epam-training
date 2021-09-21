package by.training.shapes.service;


import by.training.shapes.service.impl.BallCalculatorServiceImpl;
import by.training.shapes.service.impl.BallConditionCheckerServiceImpl;
import by.training.shapes.service.impl.BallRepositoryFillerServiceImpl;

/**
 * The class {@code ServiceFactory} is utility class
 * that provides the implementation of services.
 *
 * @author Nikita Romanov
 */
public final class ServiceFactory {

    /**
     * Singleton instance.
     */
    private static ServiceFactory instance;

    private final BallCalculatorService ballCalculatorService;
    private final BallConditionCheckerService ballConditionCheckerService;
    private final RepositoryFillerService repositoryFillerService;

    private ServiceFactory() {
        ballCalculatorService = new BallCalculatorServiceImpl();
        ballConditionCheckerService = new BallConditionCheckerServiceImpl();
        repositoryFillerService = new BallRepositoryFillerServiceImpl();
    }

    public BallCalculatorService getBallCalculatorService() {
        return ballCalculatorService;
    }

    public BallConditionCheckerService getBallConditionCheckerService() {
        return ballConditionCheckerService;
    }

    public RepositoryFillerService getBallRepositoryFillerService() {
        return repositoryFillerService;
    }

    /**
     * Getter for singleton instance.
     *
     * @return instance of this class.
     */
    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }
}
