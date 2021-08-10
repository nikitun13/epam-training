package by.training.arrays.service.factory;

import by.training.arrays.service.MatrixCreatorService;
import by.training.arrays.service.MatrixOperationsService;
import by.training.arrays.service.impl.MatrixCreatorServiceImpl;
import by.training.arrays.service.impl.MatrixOperationsServiceImpl;

/**
 * The class {@code ServiceFactory} is utility class
 * that provides the implementation of services.
 *
 * @author Nikita Romanov
 */
public final class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final MatrixCreatorService matrixCreatorService = new MatrixCreatorServiceImpl();
    private final MatrixOperationsService matrixOperationsService = new MatrixOperationsServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public MatrixCreatorService getMatrixCreatorService() {
        return matrixCreatorService;
    }

    public MatrixOperationsService getMatrixOperationsService() {
        return matrixOperationsService;
    }
}
