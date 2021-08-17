package by.training.arrays.service;

import by.training.arrays.service.impl.ArrayCreatorServiceImpl;
import by.training.arrays.service.impl.MatrixCreatorServiceImpl;
import by.training.arrays.service.impl.MatrixOperationsServiceImpl;
import by.training.arrays.service.impl.sort.ArraySortingServiceProvider;

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
    private final ArrayCreatorService arrayCreatorService = new ArrayCreatorServiceImpl();
    private final ArraySortingServiceProvider arraySortingServiceProvider = ArraySortingServiceProvider.getInstance();

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

    public ArrayCreatorService getArrayCreatorService() {
        return arrayCreatorService;
    }

    public ArraySortingService getArraySortingService(String sortName) {
        return arraySortingServiceProvider.getService(sortName);
    }
}
