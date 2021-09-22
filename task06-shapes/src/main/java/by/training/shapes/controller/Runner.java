package by.training.shapes.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code Runner} is a main class
 * that launches the application.
 *
 * @author Nikita Romanov
 */
public class Runner {

    private static final Logger log = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        log.info("application started");
        Controller controller = new ControllerImpl();
        controller.run();
        log.info("application finished");
    }
}
