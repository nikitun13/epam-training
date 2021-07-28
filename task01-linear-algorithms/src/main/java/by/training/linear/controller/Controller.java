package by.training.linear.controller;

import by.training.linear.entity.Circle;
import by.training.linear.entity.Resistor;
import by.training.linear.exception.InvalidCircleException;
import by.training.linear.exception.InvalidInputException;
import by.training.linear.exception.InvalidResistorException;
import by.training.linear.exception.MenuException;
import by.training.linear.model.*;
import by.training.linear.view.ConsoleView;
import by.training.linear.view.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Comparator.comparing;

public class Controller {

    private static final Logger logger = LogManager.getLogger(Controller.class);
    private static final String INVALID_USER_INPUT_LOG_MESSAGE = "Invalid user input:";

    private final ConsoleView view;
    private final AverageFinder averageFinder;
    private final PowerCalculator powerCalculator;
    private final CircleCalculator circleCalculator;
    private final ResistanceCalculator resistanceCalculator;
    private final ExpressionCalculator expressionCalculator;

    private static final int FIRST_TASK = 1;
    private static final int SECOND_TASK = 2;
    private static final int THIRD_TASK = 3;
    private static final int FOURTH_TASK = 4;
    private static final int FIFTH_TASK = 5;

    private static final int FIRST_POWER = 1;
    private static final int SECOND_POWER = 2;
    private static final int THIRD_POWER = 3;
    private static final int FOURTH_POWER = 4;

    public Controller(ConsoleView view,
                      AverageFinder averageFinder,
                      PowerCalculator powerCalculator,
                      CircleCalculator circleCalculator,
                      ResistanceCalculator resistanceCalculator,
                      ExpressionCalculator expressionCalculator) {
        this.view = view;
        this.averageFinder = averageFinder;
        this.circleCalculator = circleCalculator;
        this.expressionCalculator = expressionCalculator;
        this.powerCalculator = powerCalculator;
        this.resistanceCalculator = resistanceCalculator;
    }

    public void run() {
        logger.debug("enter infinity loop");
        while (true) {
            showMenu();
            try {
                int choice = view.scanInt();
                if (choice == 0) {
                    break;
                }
                view.printNewLine();
                switchUserChoice(choice);
            } catch (InvalidInputException | MenuException e) {
                logger.error(INVALID_USER_INPUT_LOG_MESSAGE, e);
                view.printNewLine();
                view.println(ConsoleView.INVALID_INPUT_MESSAGE);
            }
        }
        logger.debug("exit infinity loop");
    }

    private void showMenu() {
        view.printNewLine();
        view.println(ConsoleView.CHOOSE_OPTION_MESSAGE);
        Stream.of(MenuItem.values())
                .sorted(comparing(MenuItem::getNum))
                .forEach(item -> view.println(item.toString()));
        view.print(ConsoleView.YOUR_CHOICE_MESSAGE);
    }

    private void switchUserChoice(int choice) {
        switch (choice) {
            case FIRST_TASK -> averageFinderTask();
            case SECOND_TASK -> powerCalculatorTask();
            case THIRD_TASK -> circleCalculatorTask();
            case FOURTH_TASK -> resistanceCalculatorTask();
            case FIFTH_TASK -> expressionCalculatorTask();
            default -> throw new MenuException("Unexpected value: " + choice);
        }
    }

    // first task
    private void averageFinderTask() {
        try {
            view.print(ConsoleView.ENTER_FIRST_NUMBER);
            double firstNumber = view.scanDouble();
            view.print(ConsoleView.ENTER_SECOND_NUMBER);
            double secondNumber = view.scanDouble();
            double result = averageFinder.average(firstNumber, secondNumber);
            view.println(ConsoleView.RESULT + result);
        } catch (InvalidInputException e) {
            logger.error(INVALID_USER_INPUT_LOG_MESSAGE, e);
            view.println(ConsoleView.INVALID_INPUT_MESSAGE);
        }
    }

    //second task
    private void powerCalculatorTask() {
        double firstResult = powerCalculator.powPi(FIRST_POWER);
        double secondResult = powerCalculator.powPi(SECOND_POWER);
        double thirdResult = powerCalculator.powPi(THIRD_POWER);
        double fourthResult = powerCalculator.powPi(FOURTH_POWER);

        view.println(ConsoleView.RESULT);
        view.println(format(ConsoleView.FORMATTED_POWER_OF_PI, FIRST_POWER, firstResult));
        view.println(format(ConsoleView.FORMATTED_POWER_OF_PI, SECOND_POWER, secondResult));
        view.println(format(ConsoleView.FORMATTED_POWER_OF_PI, THIRD_POWER, thirdResult));
        view.println(format(ConsoleView.FORMATTED_POWER_OF_PI, FOURTH_POWER, fourthResult));
    }

    // third task
    private void circleCalculatorTask() {
        try {
            view.print(ConsoleView.ENTER_CIRCUMFERENCE);
            double circumference = view.scanDouble();
            Circle circle = new Circle(circumference);
            double result = circleCalculator.calculateCircleArea(circle);
            view.println(ConsoleView.RESULT + result);
        } catch (InvalidInputException e) {
            logger.error(INVALID_USER_INPUT_LOG_MESSAGE, e);
            view.println(ConsoleView.INVALID_INPUT_MESSAGE);
        } catch (InvalidCircleException e) {
            logger.error("Invalid circle:", e);
            view.println(ConsoleView.INVALID_CIRCLE_MESSAGE);
        }
    }

    // fourth task
    private void resistanceCalculatorTask() {
        try {
            view.print(ConsoleView.ENTER_RESISTANCE_OF_FIRST_RESISTOR);
            double firstResistance = view.scanDouble();
            view.print(ConsoleView.ENTER_RESISTANCE_OF_SECOND_RESISTOR);
            double secondResistance = view.scanDouble();
            view.print(ConsoleView.ENTER_RESISTANCE_OF_THIRD_RESISTOR);
            double thirdResistance = view.scanDouble();

            List<Resistor> resistors = List.of(
                    new Resistor(firstResistance),
                    new Resistor(secondResistance),
                    new Resistor(thirdResistance)
            );
            double result = resistanceCalculator.calculateResistanceOfParallelResistors(resistors);
            view.println(ConsoleView.RESULT + result);
        } catch (InvalidInputException e) {
            logger.error(INVALID_USER_INPUT_LOG_MESSAGE, e);
            view.println(ConsoleView.INVALID_INPUT_MESSAGE);
        } catch (InvalidResistorException e) {
            logger.error("Invalid resistor:", e);
            view.println(ConsoleView.INVALID_RESISTOR_MESSAGE);
        }
    }

    // fifth task
    private void expressionCalculatorTask() {
        try {
            view.print(ConsoleView.ENTER_X);
            long x = view.scanLong();
            long firstExpressionResult = expressionCalculator.calculateFirstExpression(x);
            long secondExpressionResult = expressionCalculator.calculateSecondExpression(x);

            view.println(ConsoleView.RESULT);
            view.println(ConsoleView.FIRST_EXPRESSION + firstExpressionResult);
            view.println(ConsoleView.SECOND_EXPRESSION + secondExpressionResult);
        } catch (InvalidInputException e) {
            logger.error(INVALID_USER_INPUT_LOG_MESSAGE, e);
            view.println(ConsoleView.INVALID_INPUT_MESSAGE);
        }
    }
}
