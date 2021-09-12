package by.training.multithreading.controller;

import by.training.multithreading.view.ConsoleView;
import by.training.multithreading.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {

    private static final Logger log = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        log.info("application is started");

        String pathToMatrix = "src/main/resources/matrix/03-matrix.txt";
        String pathToConfig = "src/main/resources/thread/02-config.txt";
        View view = new ConsoleView();
        Controller controller
                = new Controller(pathToMatrix, pathToConfig, view);

        controller.executeTask("lock");
        controller.executeTask("semaphore");
        controller.executeTask("atomic");
        controller.executeTask("map");
        controller.executeTask("set");

        log.info("finishing application");
    }
}
