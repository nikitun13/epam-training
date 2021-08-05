package by.training.branching.controller;

import by.training.branching.view.ConsoleView;
import by.training.branching.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code Runner} is a main class that launches the application.
 *
 * @author Nikita Romanov
 */
public class Runner {

    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        logger.info("application started");
        View view = new ConsoleView();
        Controller controller = new ControllerImpl(view);
        controller.run();
        logger.info("application finished");
    }
}
