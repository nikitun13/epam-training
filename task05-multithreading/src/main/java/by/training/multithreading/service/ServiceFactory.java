package by.training.multithreading.service;

import by.training.multithreading.service.impl.MatrixCreatorServiceImpl;

/**
 * The class {@code ServiceFactory} is utility class
 * that provides the implementation of services.
 *
 * @author Nikita Romanov
 */
public final class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final MatrixCreatorService matrixCreatorService
            = new MatrixCreatorServiceImpl();
    private final ParallelMatrixFillerServiceProvider fillerServiceProvider
            = ParallelMatrixFillerServiceProvider.getInstance();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public MatrixCreatorService getMatrixCreatorService() {
        return matrixCreatorService;
    }

    public ParallelMatrixFillerService getParallelMatrixFillerService(
            String name) {
        return fillerServiceProvider.getService(name);
    }
}
