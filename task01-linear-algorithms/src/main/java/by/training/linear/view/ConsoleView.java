package by.training.linear.view;

import by.training.linear.exception.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ConsoleView {

    private static final Logger logger = LogManager.getLogger(ConsoleView.class);

    private static final String USER_ENTER_LOGGER_MESSAGE = "User entered: {}";
    private static final String ENTER = "Enter ";

    public static final String CHOOSE_OPTION_MESSAGE = "Choose which task to solve:";
    public static final String YOUR_CHOICE_MESSAGE = "Your choice: ";
    public static final String INVALID_INPUT_MESSAGE = "Invalid input! Try again...";
    public static final String RESULT = "Result: ";

    public static final String ENTER_FIRST_NUMBER = ENTER + "first number: ";
    public static final String ENTER_SECOND_NUMBER = ENTER + "second number: ";

    public static final String FORMATTED_POWER_OF_PI = "π^%d = %f";

    public static final String ENTER_CIRCUMFERENCE = ENTER + "circumference: ";
    public static final String INVALID_CIRCLE_MESSAGE = "Circumference can't be 0 or less";

    private static final String ENTER_RESISTANCE_FORMATTED = ENTER + "resistance of %s resistor: ";
    public static final String ENTER_RESISTANCE_OF_FIRST_RESISTOR = String.format(ENTER_RESISTANCE_FORMATTED, "first");
    public static final String ENTER_RESISTANCE_OF_SECOND_RESISTOR = String.format(ENTER_RESISTANCE_FORMATTED, "second");
    public static final String ENTER_RESISTANCE_OF_THIRD_RESISTOR = String.format(ENTER_RESISTANCE_FORMATTED, "third");
    public static final String INVALID_RESISTOR_MESSAGE = "Resistance can't be 0 or less";

    public static final String FIRST_EXPRESSION = "-2x + 3x^2 - 4x^3 = ";
    public static final String SECOND_EXPRESSION = "1 + 2x + 3x^2 + 4x^3 = ";
    public static final String ENTER_X = ENTER + "x: ";

    private final Scanner scanner = new Scanner(System.in);

    public int scanInt() {
        if (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            clearInput();
            logger.debug(USER_ENTER_LOGGER_MESSAGE, num);
            return num;
        } else {
            clearInput();
            throw new InvalidInputException("Expected to receive int");
        }
    }

    public double scanDouble() {
        if (scanner.hasNextDouble()) {
            double num = scanner.nextDouble();
            clearInput();
            logger.debug(USER_ENTER_LOGGER_MESSAGE, num);
            return num;
        } else {
            clearInput();
            throw new InvalidInputException("Expected to receive double");
        }
    }

    public long scanLong() {
        if (scanner.hasNextLong()) {
            long num = scanner.nextLong();
            clearInput();
            logger.debug(USER_ENTER_LOGGER_MESSAGE, num);
            return num;
        } else {
            clearInput();
            throw new InvalidInputException("Expected to receive long");
        }
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void printNewLine() {
        System.out.println();
    }

    public void print(String message) {
        System.out.print(message);
    }

    private void clearInput() {
        scanner.nextLine();
    }
}
