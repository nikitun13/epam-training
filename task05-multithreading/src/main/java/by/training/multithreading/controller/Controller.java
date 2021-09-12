package by.training.multithreading.controller;

import by.training.multithreading.entity.Matrix;
import by.training.multithreading.service.MatrixCreatorService;
import by.training.multithreading.service.ParallelMatrixFillerService;
import by.training.multithreading.service.ServiceException;
import by.training.multithreading.service.ServiceFactory;
import by.training.multithreading.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

    private static final Logger log = LogManager.getLogger(Controller.class);

    private final String pathToMatrix;
    private final String pathToConfig;
    private final View view;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public Controller(String pathToMatrix, String pathToConfig, View view) {
        this.pathToMatrix = pathToMatrix;
        this.pathToConfig = pathToConfig;
        this.view = view;
    }

    public void executeTask(String name) {
        try {
            ParallelMatrixFillerService fillerService
                    = serviceFactory.getParallelMatrixFillerService(name);
            if (fillerService == null) {
                log.error("invalid service name: {} ", name);
                view.println("Invalid service name!");
                return;
            }
            MatrixCreatorService matrixCreatorService
                    = serviceFactory.getMatrixCreatorService();
            Matrix matrix = matrixCreatorService.createFromFile(pathToMatrix);
            fillerService.fillMatrix(matrix, pathToConfig);
            view.print("Result of %s filler:".formatted(name));
            view.println(matrix.toString());
        } catch (ServiceException e) {
            log.error(e);
            view.println("error occurred");
        }
    }
}
