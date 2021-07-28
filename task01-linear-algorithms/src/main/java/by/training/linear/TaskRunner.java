package by.training.linear;

import by.training.linear.controller.Controller;
import by.training.linear.model.*;
import by.training.linear.view.ConsoleView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaskRunner {

    private static final Logger logger = LogManager.getLogger(TaskRunner.class);

    public static void main(String[] args) {
        logger.info("application started");
        ConsoleView view = new ConsoleView();
        AverageFinder averageFinder = new AverageFinder();
        PowerCalculator powerCalculator = new PowerCalculator();
        CircleCalculator circleCalculator = new CircleCalculator();
        ResistanceCalculator resistanceCalculator = new ResistanceCalculator();
        ExpressionCalculator expressionCalculator = new ExpressionCalculator();

        new Controller(view,
                averageFinder,
                powerCalculator,
                circleCalculator,
                resistanceCalculator,
                expressionCalculator)
                .run();
        logger.info("application finished");
    }
}
